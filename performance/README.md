# Performance results

## Results for Jackson 2.11.2 and Kotlin 1.4 + Kotlin Serialization 1.0.1. 

Higher value means better result. 

```
Benchmark                                           Mode  Cnt  Score   Error   Units
PersonBenchmark.jacksonSerializePerson             thrpt   10  2.884 ± 0.323  ops/us
PersonBenchmark.kotlinSerializePerson              thrpt   10  2.592 ± 0.211  ops/us
PersonBenchmark.jacksonDeserializePerson           thrpt   10  0.894 ± 0.040  ops/us
PersonBenchmark.kotlinDeserializePerson            thrpt   10  1.719 ± 0.205  ops/us
--
PersonCollectionsBenchmark.jacksonSerializeList    thrpt   10  0.849 ± 0.046  ops/us
PersonCollectionsBenchmark.kotlinSerializeList     thrpt   10  0.521 ± 0.070  ops/us
PersonCollectionsBenchmark.jacksonDeserializeList  thrpt   10  0.166 ± 0.007  ops/us
PersonCollectionsBenchmark.kotlinDeserializeList   thrpt   10  0.319 ± 0.016  ops/us
--
PersonMapBenchmark.jacksonSerializeMap             thrpt   10  0.698 ± 0.015  ops/us
PersonMapBenchmark.kotlinSerializeMap              thrpt   10  0.450 ± 0.030  ops/us
PersonMapBenchmark.jacksonDeserializeMap           thrpt   10  0.152 ± 0.007  ops/us
PersonMapBenchmark.kotlinDeserializeMap            thrpt   10  0.281 ± 0.020  ops/us
```

Serialization with Jackson is still a bit faster. However, deserialization with Kotlin is
 significantly faster.

## Results for Jackson 2.13.4 and Kotlin 1.7.10 + Kotlin Serialization 1.3.3

```
Benchmark                                           Mode  Cnt  Score   Error   Units

PersonBenchmark.jacksonSerializePerson             thrpt   10  3.231 ± 0.260  ops/us
PersonBenchmark.kotlinSerializePerson              thrpt   10  5.224 ± 0.084  ops/us
PersonBenchmark.jacksonDeserializePerson           thrpt   10  1.040 ± 0.027  ops/us
PersonBenchmark.kotlinDeserializePerson            thrpt   10  2.061 ± 0.025  ops/us
---
PersonCollectionsBenchmark.jacksonSerializeList    thrpt   10  0.919 ± 0.036  ops/us
PersonCollectionsBenchmark.kotlinSerializeList     thrpt   10  1.112 ± 0.041  ops/us
PersonCollectionsBenchmark.jacksonDeserializeList  thrpt   10  0.195 ± 0.006  ops/us
PersonCollectionsBenchmark.kotlinDeserializeList   thrpt   10  0.383 ± 0.008  ops/us
---
PersonMapBenchmark.jacksonSerializeMap             thrpt   10  0.781 ± 0.042  ops/us
PersonMapBenchmark.kotlinSerializeMap              thrpt   10  0.914 ± 0.030  ops/us
PersonMapBenchmark.jacksonDeserializeMap           thrpt   10  0.183 ± 0.002  ops/us
PersonMapBenchmark.kotlinDeserializeMap            thrpt   10  0.331 ± 0.015  ops/us
```

## Chart

<img src="./img/PersonBenchmark - Jackson vs Kotlin.png" width="100%"/>

<img src="./img/PersonCollectionsBenchmark - Jackson vs Kotlin.png" width="100%"/>

## How to run performance tests

>  java -jar performance/target/benchmarks.jar

