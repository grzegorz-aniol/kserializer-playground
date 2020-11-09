# Performance results

Here is the result of serialization performance results for Jackson (2.11.2) and Kotlin Serialization (1.0.1). 
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
