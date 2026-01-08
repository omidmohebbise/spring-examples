# MongoDB Performance Tips

These are practical, high-impact rules of thumb for keeping MongoDB fast. They’re deliberately opinionated and optimized for day-to-day Spring + Mongo projects.

## 1) Indexing (the big lever)

- **Index every frequent filter + sort pattern.**
  - If you filter on `status` and sort by `createdAt`, you usually want a **compound index** like `(status, createdAt)`.
- **Put equality fields first, range/sort later** in compound indexes.
  - Example: `{ status: 1, createdAt: -1 }` is often better than `{ createdAt: -1, status: 1 }` when you filter by status.
- Use `explain()` on queries and look for:
  - `COLLSCAN` (bad) → full collection scan
  - `IXSCAN` (good) → index scan
- **Don’t over-index.** Every index costs RAM/disk and slows writes.

### Quick `explain()` examples

```javascript
// Good query shape for an index like { status: 1, createdAt: -1 }
db.orders.find({ status: "PAID" }).sort({ createdAt: -1 }).explain("executionStats")

// Check whether a query is using an index (IXSCAN) or scanning (COLLSCAN)
db.people.find({ nationalId: "123", passportNumber: "P9" }).explain("executionStats")
```

## 2) Return less data

- Use **projections**: fetch only the fields you need.
- Avoid huge documents. Big payloads increase latency and reduce cache efficiency.

Examples:

```javascript
// Projection: return only a couple fields
db.people.find(
  { lastName: "Mohebbi" },
  { firstName: 1, lastName: 1, _id: 0 }
)
```

## 3) Model for your reads

- **Embed** data you almost always load together.
- **Reference** data that grows independently or is updated frequently.
- Avoid “join-heavy thinking.” `$lookup` is fine, but if every request needs 3 lookups, the model is telling you something.

## 4) Avoid hot spots

- Watch out for monotonically increasing shard keys / index patterns that funnel writes to one place.
- For high-write collections, consider IDs that distribute inserts (depends on your sharding/cluster setup).

## 5) Make writes cheaper

- **Batch**: use bulk writes instead of many small calls.
- Prefer atomic updates (`$set`, `$inc`, `$push`) over read-modify-write in the app.
- Tune durability: don’t default to the strictest write concern everywhere unless you need it.

Examples:

```javascript
// Atomic update: do not fetch then save unless you need to
db.counters.updateOne({ _id: "seq" }, { $inc: { value: 1 } })
```

## 6) Aggregations: be kind to the pipeline

- Put `$match` early (and match on **indexed** fields when possible).
- Put `$project` early if it drops big fields you don’t need.
- Prefer precomputed fields for expensive computed predicates that run constantly.

## 7) Pagination without pain

- Avoid deep `skip` pagination on big collections (it gets slower as you go).
- Prefer cursor/range pagination: “give me next page where `createdAt < lastSeen`”.

## 8) Connection & app-side habits (Spring-friendly)

- Reuse connections (default pooling is good—don’t fight it).
- Keep timeouts sane (connect/read) so slow queries don’t pile up threads.
- Don’t spam the DB with N+1 patterns (easy to do when you reference a lot).

## 9) Watch the usual suspects

- Unbounded arrays inside documents (they grow forever; updates get heavier).
- Queries that sort without an index (Mongo has to sort in memory / spill to disk).
- Large `$in` lists (sometimes you need a different approach).
