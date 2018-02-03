    ======== DEBUG OUTPUT OF TESTING TOOL ========
    Using Spark's default log4j profile: org/apache/spark/log4j-defaults.properties
    18/02/03 09:09:18 WARN NativeCodeLoader: Unable to load native-hadoop library for your platform... using builtin-java classes where applicable
    [test failure log] test name: CapstoneSuite::[#1 - Data extraction] temperatures are located::5
    org.apache.hadoop.mapred.InvalidInputException: Input path does not exist: file:/grader/repository/src/main/resources/stations1.csv
    org.apache.hadoop.mapred.FileInputFormat.listStatus(FileInputFormat.java:251)
    org.apache.hadoop.mapred.FileInputFormat.getSplits(FileInputFormat.java:270)
    org.apache.spark.rdd.HadoopRDD.getPartitions(HadoopRDD.scala:202)
    org.apache.spark.rdd.RDD$$anonfun$partitions$2.apply(RDD.scala:252)
    org.apache.spark.rdd.RDD$$anonfun$partitions$2.apply(RDD.scala:250)
    scala.Option.getOrElse(Option.scala:121)
    org.apache.spark.rdd.RDD.partitions(RDD.scala:250)
    org.apache.spark.rdd.MapPartitionsRDD.getPartitions(MapPartitionsRDD.scala:35)
    org.apache.spark.rdd.RDD$$anonfun$partitions$2.apply(RDD.scala:252)
    org.apache.spark.rdd.RDD$$anonfun$partitions$2.apply(RDD.scala:250)
    scala.Option.getOrElse(Option.scala:121)
    org.apache.spark.rdd.RDD.partitions(RDD.scala:250)
    org.apache.spark.rdd.MapPartitionsRDD.getPartitions(MapPartitionsRDD.scala:35)
    org.apache.spark.rdd.RDD$$anonfun$partitions$2.apply(RDD.scala:252)
    org.apache.spark.rdd.RDD$$anonfun$partitions$2.apply(RDD.scala:250)
    scala.Option.getOrElse(Option.scala:121)
    org.apache.spark.rdd.RDD.partitions(RDD.scala:250)
    org.apache.spark.rdd.MapPartitionsRDD.getPartitions(MapPartitionsRDD.scala:35)
    org.apache.spark.rdd.RDD$$anonfun$partitions$2.apply(RDD.scala:252)
    org.apache.spark.rdd.RDD$$anonfun$partitions$2.apply(RDD.scala:250)
    scala.Option.getOrElse(Option.scala:121)
    org.apache.spark.rdd.RDD.partitions(RDD.scala:250)
    org.apache.spark.rdd.MapPartitionsRDD.getPartitions(MapPartitionsRDD.scala:35)
    org.apache.spark.rdd.RDD$$anonfun$partitions$2.apply(RDD.scala:252)
    org.apache.spark.rdd.RDD$$anonfun$partitions$2.apply(RDD.scala:250)
    
    
    [test failure log] test name: CapstoneSuite::[#1 - Data extraction] stations with no location are ignored::3
    org.apache.hadoop.mapred.InvalidInputException: Input path does not exist: file:/grader/repository/src/main/resources/empty-stations.csv
    org.apache.hadoop.mapred.FileInputFormat.listStatus(FileInputFormat.java:251)
    org.apache.hadoop.mapred.FileInputFormat.getSplits(FileInputFormat.java:270)
    org.apache.spark.rdd.HadoopRDD.getPartitions(HadoopRDD.scala:202)
    org.apache.spark.rdd.RDD$$anonfun$partitions$2.apply(RDD.scala:252)
    org.apache.spark.rdd.RDD$$anonfun$partitions$2.apply(RDD.scala:250)
    scala.Option.getOrElse(Option.scala:121)
    org.apache.spark.rdd.RDD.partitions(RDD.scala:250)
    org.apache.spark.rdd.MapPartitionsRDD.getPartitions(MapPartitionsRDD.scala:35)
    org.apache.spark.rdd.RDD$$anonfun$partitions$2.apply(RDD.scala:252)
    org.apache.spark.rdd.RDD$$anonfun$partitions$2.apply(RDD.scala:250)
    scala.Option.getOrElse(Option.scala:121)
    org.apache.spark.rdd.RDD.partitions(RDD.scala:250)
    org.apache.spark.rdd.MapPartitionsRDD.getPartitions(MapPartitionsRDD.scala:35)
    org.apache.spark.rdd.RDD$$anonfun$partitions$2.apply(RDD.scala:252)
    org.apache.spark.rdd.RDD$$anonfun$partitions$2.apply(RDD.scala:250)
    scala.Option.getOrElse(Option.scala:121)
    org.apache.spark.rdd.RDD.partitions(RDD.scala:250)
    org.apache.spark.rdd.MapPartitionsRDD.getPartitions(MapPartitionsRDD.scala:35)
    org.apache.spark.rdd.RDD$$anonfun$partitions$2.apply(RDD.scala:252)
    org.apache.spark.rdd.RDD$$anonfun$partitions$2.apply(RDD.scala:250)
    scala.Option.getOrElse(Option.scala:121)
    org.apache.spark.rdd.RDD.partitions(RDD.scala:250)
    org.apache.spark.rdd.MapPartitionsRDD.getPartitions(MapPartitionsRDD.scala:35)
    org.apache.spark.rdd.RDD$$anonfun$partitions$2.apply(RDD.scala:252)
    org.apache.spark.rdd.RDD$$anonfun$partitions$2.apply(RDD.scala:250)
    
    
    [test failure log] test name: CapstoneSuite::[#1 - Data extraction] weather stations are identified by the composite (STN, WBAN)::3
    org.apache.hadoop.mapred.InvalidInputException: Input path does not exist: file:/grader/repository/src/main/resources/stations2.csv
    org.apache.hadoop.mapred.FileInputFormat.listStatus(FileInputFormat.java:251)
    org.apache.hadoop.mapred.FileInputFormat.getSplits(FileInputFormat.java:270)
    org.apache.spark.rdd.HadoopRDD.getPartitions(HadoopRDD.scala:202)
    org.apache.spark.rdd.RDD$$anonfun$partitions$2.apply(RDD.scala:252)
    org.apache.spark.rdd.RDD$$anonfun$partitions$2.apply(RDD.scala:250)
    scala.Option.getOrElse(Option.scala:121)
    org.apache.spark.rdd.RDD.partitions(RDD.scala:250)
    org.apache.spark.rdd.MapPartitionsRDD.getPartitions(MapPartitionsRDD.scala:35)
    org.apache.spark.rdd.RDD$$anonfun$partitions$2.apply(RDD.scala:252)
    org.apache.spark.rdd.RDD$$anonfun$partitions$2.apply(RDD.scala:250)
    scala.Option.getOrElse(Option.scala:121)
    org.apache.spark.rdd.RDD.partitions(RDD.scala:250)
    org.apache.spark.rdd.MapPartitionsRDD.getPartitions(MapPartitionsRDD.scala:35)
    org.apache.spark.rdd.RDD$$anonfun$partitions$2.apply(RDD.scala:252)
    org.apache.spark.rdd.RDD$$anonfun$partitions$2.apply(RDD.scala:250)
    scala.Option.getOrElse(Option.scala:121)
    org.apache.spark.rdd.RDD.partitions(RDD.scala:250)
    org.apache.spark.rdd.MapPartitionsRDD.getPartitions(MapPartitionsRDD.scala:35)
    org.apache.spark.rdd.RDD$$anonfun$partitions$2.apply(RDD.scala:252)
    org.apache.spark.rdd.RDD$$anonfun$partitions$2.apply(RDD.scala:250)
    scala.Option.getOrElse(Option.scala:121)
    org.apache.spark.rdd.RDD.partitions(RDD.scala:250)
    org.apache.spark.rdd.MapPartitionsRDD.getPartitions(MapPartitionsRDD.scala:35)
    org.apache.spark.rdd.RDD$$anonfun$partitions$2.apply(RDD.scala:252)
    org.apache.spark.rdd.RDD$$anonfun$partitions$2.apply(RDD.scala:250)
    
    
    18/02/03 09:09:30 WARN TaskSetManager: Stage 2 contains a task of very large size (56646 KB). The maximum recommended task size is 100 KB.
    [test failure log] test name: CapstoneSuite::[#2 - Raw data display] predicted temperature at location z should be closer to known temperature at location x than to known temperature at location y, if z is closer (in distance) to x than y, and vice versa::10
    java.util.NoSuchElementException: next on empty iterator
    scala.collection.Iterator$$anon$2.next(Iterator.scala:39)
    scala.collection.Iterator$$anon$2.next(Iterator.scala:37)
    scala.collection.IndexedSeqLike$Elements.next(IndexedSeqLike.scala:63)
    scala.collection.IterableLike$class.head(IterableLike.scala:107)
    scala.collection.mutable.WrappedArray.scala$collection$IndexedSeqOptimized$$super$head(WrappedArray.scala:35)
    scala.collection.IndexedSeqOptimized$class.head(IndexedSeqOptimized.scala:126)
    scala.collection.mutable.WrappedArray.head(WrappedArray.scala:35)
    observatory.Visualization$.shepardsMethod$1(Visualization.scala:87)
    observatory.Visualization$.predictTemperature(Visualization.scala:115)
    observatory.VisualizationTest$$anonfun$4$$anonfun$apply$mcV$sp$1$$anonfun$apply$mcVI$sp$1.apply$mcVI$sp(VisualizationTest.scala:92)
    scala.collection.immutable.Range.foreach$mVc$sp(Range.scala:160)
    observatory.VisualizationTest$$anonfun$4$$anonfun$apply$mcV$sp$1.apply$mcVI$sp(VisualizationTest.scala:89)
    scala.collection.immutable.Range.foreach$mVc$sp(Range.scala:160)
    observatory.VisualizationTest$$anonfun$4.apply$mcV$sp(VisualizationTest.scala:88)
    observatory.MilestoneSuite$$anonfun$namedMilestoneTest$1.apply$mcV$sp(CapstoneSuite.scala:106)
    ch.epfl.lamp.grading.GradingSuite$$anonfun$test$1.apply$mcV$sp(GradingSuite.scala:124)
    ch.epfl.lamp.grading.GradingSuite$$anonfun$test$1.apply(GradingSuite.scala:122)
    ch.epfl.lamp.grading.GradingSuite$$anonfun$test$1.apply(GradingSuite.scala:122)
    org.scalatest.Transformer$$anonfun$apply$1.apply$mcV$sp(Transformer.scala:22)
    org.scalatest.OutcomeOf$class.outcomeOf(OutcomeOf.scala:85)
    org.scalatest.OutcomeOf$.outcomeOf(OutcomeOf.scala:104)
    org.scalatest.Transformer.apply(Transformer.scala:22)
    org.scalatest.Transformer.apply(Transformer.scala:20)
    org.scalatest.FunSuiteLike$$anon$1.apply(FunSuiteLike.scala:166)
    org.scalatest.Suite$class.withFixture(Suite.scala:1122)
    
    
    Row 0 completed.
    Row 1 completed.
    Row 2 completed.
    Row 3 completed.
    Row 4 completed.
    Row 5 completed.
    Row 6 completed.
    Row 7 completed.
    Row 8 completed.
    Row 9 completed.
    Row 10 completed.
    Row 11 completed.
    Row 12 completed.
    Row 13 completed.
    Row 14 completed.
    Row 15 completed.
    Row 16 completed.
    Row 17 completed.
    Row 18 completed.
    Row 19 completed.
    Row 20 completed.
    Row 21 completed.
    Row 22 completed.
    Row 23 completed.
    Row 24 completed.
    Row 25 completed.
    Row 26 completed.
    Row 27 completed.
    Row 28 completed.
    Row 29 completed.
    Row 30 completed.
    Row 31 completed.
    Row 32 completed.
    Row 33 completed.
    Row 34 completed.
    Row 35 completed.
    Row 36 completed.
    Row 37 completed.
    Row 38 completed.
    Row 39 completed.
    Row 40 completed.
    Row 41 completed.
    Row 42 completed.
    Row 43 completed.
    Row 44 completed.
    Row 45 completed.
    Row 46 completed.
    Row 47 completed.
    Row 48 completed.
    Row 49 completed.
    Row 50 completed.
    Row 51 completed.
    Row 52 completed.
    Row 53 completed.
    Row 54 completed.
    Row 55 completed.
    Row 56 completed.
    Row 57 completed.
    Row 58 completed.
    Row 59 completed.
    Row 60 completed.
    Row 61 completed.
    Row 62 completed.
    Row 63 completed.
    Row 64 completed.
    Row 65 completed.
    Row 66 completed.
    Row 67 completed.
    Row 68 completed.
    Row 69 completed.
    Row 70 completed.
    Row 71 completed.
    Row 72 completed.
    Row 73 completed.
    Row 74 completed.
    Row 75 completed.
    Row 76 completed.
    Row 77 completed.
    Row 78 completed.
    Row 79 completed.
    Row 80 completed.
    Row 81 completed.
    Row 82 completed.
    Row 83 completed.
    Row 84 completed.
    Row 85 completed.
    Row 86 completed.
    Row 87 completed.
    Row 88 completed.
    Row 89 completed.
    Row 90 completed.
    Row 91 completed.
    Row 92 completed.
    Row 93 completed.
    Row 94 completed.
    Row 95 completed.
    Row 96 completed.
    Row 97 completed.
    Row 98 completed.
    Row 99 completed.
    Row 100 completed.
    Row 101 completed.
    Row 102 completed.
    Row 103 completed.
    Row 104 completed.
    Row 105 completed.
    Row 106 completed.
    Row 107 completed.
    Row 108 completed.
    Row 109 completed.
    Row 110 completed.
    Row 111 completed.
    Row 112 completed.
    Row 113 completed.
    Row 114 completed.
    Row 115 completed.
    Row 116 completed.
    Row 117 completed.
    Row 118 completed.
    Row 119 completed.
    Row 120 completed.
    Row 121 completed.
    Row 122 completed.
    Row 123 completed.
    Row 124 completed.
    Row 125 completed.
    Row 126 completed.
    Row 127 completed.
    Row 128 completed.
    Row 129 completed.
    Row 130 completed.
    Row 131 completed.
    Row 132 completed.
    Row 133 completed.
    Row 134 completed.
    Row 135 completed.
    Row 136 completed.
    Row 137 completed.
    Row 138 completed.
    Row 139 completed.
    Row 140 completed.
    Row 141 completed.
    Row 142 completed.
    Row 143 completed.
    Row 144 completed.
    Row 145 completed.
    Row 146 completed.
    Row 147 completed.
    Row 148 completed.
    Row 149 completed.
    Row 150 completed.
    Row 151 completed.
    Row 152 completed.
    Row 153 completed.
    Row 154 completed.
    Row 155 completed.
    Row 156 completed.
    Row 157 completed.
    Row 158 completed.
    Row 159 completed.
    Row 160 completed.
    Row 161 completed.
    Row 162 completed.
    Row 163 completed.
    Row 164 completed.
    Row 165 completed.
    Row 166 completed.
    Row 167 completed.
    Row 168 completed.
    Row 169 completed.
    Row 170 completed.
    Row 171 completed.
    Row 172 completed.
    Row 173 completed.
    Row 174 completed.
    Row 175 completed.
    Row 176 completed.
    Row 177 completed.
    Row 178 completed.
    Row 179 completed.
    ======== CODING STYLE ISSUES ========
    Checking file /grader/repository/courses/capstone/src/main/scala/observatory/package.scala... OK!
    Checking file /grader/repository/courses/capstone/src/main/scala/observatory/Main.scala... OK!
    Checking file /grader/repository/courses/capstone/src/main/scala/observatory/Interaction2.scala... OK!
    Checking file /grader/repository/courses/capstone/src/main/scala/observatory/models.scala... OK!
    Checking file /grader/repository/courses/capstone/src/main/scala/observatory/Grading.scala... OK!
    Checking file /grader/repository/courses/capstone/src/main/scala/observatory/Manipulation.scala... OK!
    Checking file /grader/repository/courses/capstone/src/main/scala/observatory/Interaction.scala... OK!
    Checking file /grader/repository/courses/capstone/src/main/scala/observatory/Visualization.scala...
      1. warning at line 91 character 26:
         Avoid using return
      2. warning at line 109 character 24:
         Avoid using return
      3. warning at line 170 character 44:
         Avoid using return
      4. warning at line 17 character 6:
         Method is longer than 50 lines
      5. warning at line 124 character 6:
         Method is longer than 50 lines
    Checking file /grader/repository/courses/capstone/src/main/scala/observatory/Visualization2.scala... OK!
    Checking file /grader/repository/courses/capstone/src/main/scala/observatory/Signal.scala... OK!
    Checking file /grader/repository/courses/capstone/src/main/scala/observatory/Extraction.scala... OK!
    
    Processed 11  file(s)
    Found 0 errors
    Found 5 warnings
