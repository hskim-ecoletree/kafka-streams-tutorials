/*****************************************************************
 * Copyright (c) 2017 EcoleTree. All Rights Reserved.
 *
 * Author : HyungSeok Kim
 * Create Date : 2023. 12. 12.
 * File Name : KafkaConfig.java
 * DESC :
 *****************************************************************/
package me.study.kafka.streams.tutorials.utils;

import org.apache.kafka.common.serialization.Serdes;
import org.apache.kafka.streams.StreamsConfig;

import java.util.Map;
import java.util.Optional;
import java.util.function.BinaryOperator;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class KafkaConfigUtil {

    public static Map<String, Object> streamsProperties(final Map<String, Object> props) {
        final Map<String, Object> defaultProps = Map.of(
                StreamsConfig.APPLICATION_ID_CONFIG, "et-kafka-streams-tutorials",
                StreamsConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:29092,localhost:29094,localhost:29095",
                StreamsConfig.DEFAULT_KEY_SERDE_CLASS_CONFIG, Serdes.String().getClass().getName(),
                StreamsConfig.DEFAULT_VALUE_SERDE_CLASS_CONFIG, Serdes.String().getClass().getName(),
                StreamsConfig.NUM_STREAM_THREADS_CONFIG, 6
        );

        return merge(defaultProps, props, (v1, v2) -> v2);
    }

    public static <K, V> Map<K, V> merge(final Map<K, V> map1, final Map<K, V> map2, final BinaryOperator<V> mergeFunction) {
        return Stream.concat(Optional.ofNullable(map1).stream(), Optional.ofNullable(map2).stream())
                .flatMap(map -> map.entrySet().stream())
                .filter(entry -> entry.getKey() != null && entry.getValue() != null)
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, mergeFunction));
    }
}
