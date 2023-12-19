/*****************************************************************
 * Copyright (c) 2017 EcoleTree. All Rights Reserved.
 *
 * Author : HyungSeok Kim
 * Create Date : 2023. 12. 12.
 * File Name : Pt01KStream.java
 * DESC :
 *****************************************************************/
package me.study.kafka.streams.tutorials.ch1_streams_dsl;

import org.apache.kafka.common.serialization.Serdes;
import org.apache.kafka.streams.StreamsBuilder;
import org.apache.kafka.streams.kstream.Consumed;
import org.apache.kafka.streams.kstream.Produced;

public class Pt01_Kstream {
    public static final String INPUT_TOPIC = "tutorial-src-for-kstream";
    public static final String OUTPUT_TOPIC = "tutorial-sink-for-kstream";

    public static StreamsBuilder build(final StreamsBuilder builder) {
        builder.stream(INPUT_TOPIC, Consumed.with(Serdes.String(), Serdes.String()))
                .mapValues(value -> value.toUpperCase())
                .to(OUTPUT_TOPIC, Produced.with(Serdes.String(), Serdes.String()));

        return builder;
    }
}
