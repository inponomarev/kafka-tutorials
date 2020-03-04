package io.confluent.developer;

import org.apache.kafka.clients.admin.AdminClient;
import org.apache.kafka.clients.admin.NewTopic;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import static java.lang.Integer.parseInt;
import static java.lang.Short.parseShort;

public class RunningAverage {

  //region createTopics

  /**
   * Create topics using AdminClient API
   */
  private void createTopics(Properties envProps) {
    Map<String, Object> config = new HashMap<>();

    config.put("bootstrap.servers", envProps.getProperty("bootstrap.servers"));
    AdminClient client = AdminClient.create(config);

    List<NewTopic> topics = new ArrayList<>();
    
    topics.add(new NewTopic(
        envProps.getProperty("input.ratings.topic.name"),
        parseInt(envProps.getProperty("input.ratings.topic.partitions")),
        parseShort(envProps.getProperty("input.ratings.topic.replication.factor"))));
    
    topics.add(new NewTopic(
        envProps.getProperty("output.rating-sum.topic.name"),
        parseInt(envProps.getProperty("output.rating-sum.topic.partitions")),
        parseShort(envProps.getProperty("output.rating-sum.topic.replication.factor"))));

    topics.add(new NewTopic(
        envProps.getProperty("output.rating-sum.topic.name"),
        parseInt(envProps.getProperty("output.rating-sum.topic.partitions")),
        parseShort(envProps.getProperty("output.rating-sum.topic.replication.factor"))));

    topics.add(new NewTopic(
        envProps.getProperty("output.rating-counts.topic.name"),
        parseInt(envProps.getProperty("output.rating-counts.topic.partitions")),
        parseShort(envProps.getProperty("output.rating-counts.topic.replication.factor"))));

    topics.add(new NewTopic(
        envProps.getProperty("output.rating-averages.topic.name"),
        parseInt(envProps.getProperty("output.rating-averages.topic.partitions")),
        parseShort(envProps.getProperty("output.rating-averages.topic.replication.factor"))));

    client.createTopics(topics);
    client.close();

  }
  //endregion
}
