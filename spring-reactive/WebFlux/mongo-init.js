db.createUser(
        {
            user: "omid",
            pwd: "12345678",
            roles: [
                {
                    role: "readWrite",
                    db: "test_db"
                }
            ]
        }
);
