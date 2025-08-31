package com.example.jounralrestapi;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;
import org.junit.jupiter.api.Test;

public class MongoTest {

    @Test
    public void testConnection() {
        // Replace with your actual MongoDB Atlas URI
        //String uri = "mongodb+srv://vcartproject:DN7YRhfpnHMN0o9B@cluster0.al87hbm.mongodb.net/?retryWrites=true&w=majority&appName=Cluster0";

        String uri = "mongodb://vcartproject:DN7YRhfpnHMN0o9B@ac-l38r1ll-shard-00-02.al87hbm.mongodb.net,ac-l38r1ll-shard-00-01.al87hbm.mongodb.net,ac-l38r1ll-shard-00-00.al87hbm.mongodb.net/?ssl=true&replicaSet=atlas-l38r1ll-shard-0&authSource=admin&retryWrites=true&w=majority&appName=Cluster0";
        try (MongoClient mongoClient = MongoClients.create(uri)) {
            MongoDatabase db = mongoClient.getDatabase("test"); // use your DB name
            System.out.println("✅ Connected to DB: " + db.getName());

            for (String name : db.listCollectionNames()) {
                System.out.println("📂 Collection: " + name);
            }
        }
    }
}
