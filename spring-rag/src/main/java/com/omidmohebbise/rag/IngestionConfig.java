package com.omidmohebbise.rag;

import java.util.List;
import java.util.Map;

import org.springframework.ai.document.Document;
import org.springframework.ai.vectorstore.VectorStore;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class IngestionConfig {

    @Bean
    CommandLineRunner ingest(VectorStore vectorStore) {
        return args -> {
            List<Document> docs = List.of(

                    new Document("""
                    Age group: 2-5
                    Principles:
                    - Feel safe, loved, and heard.
                    - Learn simple routines: sleep, food, play, cleanup.
                    - Practice sharing, taking turns, and naming feelings.
                    - Build curiosity through play, stories, and movement.
                    - Learn that mistakes are normal.
                    """,
                            Map.of(
                                    "ageGroup", "2-5",
                                    "minAge", 2,
                                    "maxAge", 5,
                                    "category", "life-principles"
                            )
                    ),

                    new Document("""
                    Age group: 5-10
                    Principles:
                    - Build kindness, honesty, and responsibility.
                    - Learn to read, ask questions, and stay curious.
                    - Practice patience and finishing small tasks.
                    - Make friends, solve little conflicts, and include others.
                    - Learn that effort matters more than being the best.
                    """,
                            Map.of(
                                    "ageGroup", "5-10",
                                    "minAge", 5,
                                    "maxAge", 10,
                                    "category", "life-principles"
                            )
                    ),

                    new Document("""
                    Age group: 10-16
                    Principles:
                    - Build self-respect and emotional awareness.
                    - Learn discipline in study, sleep, and habits.
                    - Choose friends carefully.
                    - Understand that attention is precious; protect it.
                    - Start learning how actions create consequences.
                    """,
                            Map.of(
                                    "ageGroup", "10-16",
                                    "minAge", 10,
                                    "maxAge", 16,
                                    "category", "life-principles"
                            )
                    ),

                    new Document("""
                    Age group: 16-21
                    Principles:
                    - Explore identity without rushing permanent decisions.
                    - Learn how to study, work, and manage time.
                    - Build basic money habits.
                    - Protect your body, mind, and future from reckless choices.
                    - Learn that confidence comes from doing hard things.
                    """,
                            Map.of(
                                    "ageGroup", "16-21",
                                    "minAge", 16,
                                    "maxAge", 21,
                                    "category", "life-principles"
                            )
                    ),

                    new Document("""
                    Age group: 21-25
                    Principles:
                    - Invest in skills more than image.
                    - Choose relationships that bring peace, not chaos.
                    - Learn to live below your means.
                    - Do not confuse motion with progress.
                    - Build health routines before life gets louder.
                    """,
                            Map.of(
                                    "ageGroup", "21-25",
                                    "minAge", 21,
                                    "maxAge", 25,
                                    "category", "life-principles"
                            )
                    ),

                    new Document("""
                    Age group: 25-30
                    Principles:
                    - Pick a direction and commit long enough to see results.
                    - Build credibility through consistency.
                    - Save money and avoid lifestyle inflation.
                    - Learn to say no to distractions.
                    - Choose long-term values over short-term ego.
                    """,
                            Map.of(
                                    "ageGroup", "25-30",
                                    "minAge", 25,
                                    "maxAge", 30,
                                    "category", "life-principles"
                            )
                    ),

                    new Document("""
                    Age group: 30-35
                    Principles:
                    - Protect your energy as much as your time.
                    - Build systems, not just goals.
                    - Strengthen family, friendships, and health.
                    - Accept that you cannot do everything at once.
                    - Focus on depth instead of constant expansion.
                    """,
                            Map.of(
                                    "ageGroup", "30-35",
                                    "minAge", 30,
                                    "maxAge", 35,
                                    "category", "life-principles"
                            )
                    ),

                    new Document("""
                    Age group: 35-45
                    Principles:
                    - Lead with steadiness, not noise.
                    - Invest in health before problems become expensive.
                    - Be present with children, partner, and parents.
                    - Trade status games for meaningful work.
                    - Review whether success still matches your values.
                    """,
                            Map.of(
                                    "ageGroup", "35-45",
                                    "minAge", 35,
                                    "maxAge", 45,
                                    "category", "life-principles"
                            )
                    ),

                    new Document("""
                    Age group: 45-55
                    Principles:
                    - Simplify where possible.
                    - Maintain strength, mobility, and sleep.
                    - Mentor others.
                    - Reassess money, purpose, and relationships.
                    - Protect peace from unnecessary drama.
                    """,
                            Map.of(
                                    "ageGroup", "45-55",
                                    "minAge", 45,
                                    "maxAge", 55,
                                    "category", "life-principles"
                            )
                    ),

                    new Document("""
                    Age group: 55-65
                    Principles:
                    - Prepare actively for the next chapter.
                    - Stay socially connected.
                    - Keep learning and moving.
                    - Shift from proving yourself to contributing wisely.
                    - Organize finances, health, and legal matters clearly.
                    """,
                            Map.of(
                                    "ageGroup", "55-65",
                                    "minAge", 55,
                                    "maxAge", 65,
                                    "category", "life-principles"
                            )
                    ),

                    new Document("""
                    Age group: 65-75
                    Principles:
                    - Protect dignity, independence, and community.
                    - Keep body and mind engaged.
                    - Share stories, values, and lessons with younger people.
                    - Accept help without giving up agency.
                    - Spend time on what feels meaningful now.
                    """,
                            Map.of(
                                    "ageGroup", "65-75",
                                    "minAge", 65,
                                    "maxAge", 75,
                                    "category", "life-principles"
                            )
                    ),

                    new Document("""
                    Age group: 76+
                    Principles:
                    - Prioritize comfort, connection, and peace.
                    - Let go of old grudges.
                    - Stay mentally and socially engaged.
                    - Receive care with dignity and clarity.
                    - Focus on love, presence, memory, and legacy.
                    """,
                            Map.of(
                                    "ageGroup", "76+",
                                    "minAge", 76,
                                    "maxAge", 120,
                                    "category", "life-principles"
                            )
                    )
            );

            vectorStore.add(docs);
        };
    }
}
