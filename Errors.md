###First commit.

    |OK|  Test Description] [#1 - Data extraction] weather stations are identified by the composite (STN, WBAN)</br>
    [Observed Error] Input path does not exist: file:/grader/repository/src/main/resources/stations2.csv</br>
    [exception was thrown] detailed error message in debug output section below</br>
    [Lost Points] 3

    |OK|  [Test Description] [#1 - Data extraction] temperatures are located</br>
    [Observed Error] Input path does not exist: file:/grader/repository/src/main/resources/stations1.csv</br>
    [exception was thrown] detailed error message in debug output section below</br>
    [Lost Points] 5

    |OK|  [Test Description] [#1 - Data extraction] stations with no location are ignored</br></br>
    [Observed Error] Input path does not exist: file:/grader/repository/src/main/resources/empty-stations.csv</br>
    [exception was thrown] detailed error message in debug output section below</br>
    [Lost Points] 3


###Second commit.

    |OLD|  [Test Description] [#2 - Raw data display] visualize
    [Observed Error] NoSuchElementException was thrown during property evaluation.
      Message: head of empty list
      Occurred when passed generated values (
        arg0 = 19.541567084835435,
        arg1 = 1.9539411255486812
      )
    [Lost Points] 5
    
    |OK|  [Test Description] [#2 - Raw data display] visualize
    [Observed Error] GeneratorDrivenPropertyCheckFailedException was thrown during property evaluation.
     (VisualizationTest.scala:133)
      Falsified after 0 successful property evaluations.
      Location: (VisualizationTest.scala:133)
      Occurred when passed generated values (
        arg0 = 72.41099618512791,
        arg1 = 0.0
      )
      Label of failing property:
        Incorrect computed color at Location(49.0,-180.0): Color(0,0,255). Expected to be closer to Color(255,0,0) than Color(0,0,255)
    [Lost Points] 5

    |OK|  [Test Description] [#2 - Raw data display] basic color interpolation
    [Observed Error] Color(127,0,127) did not equal Color(128,0,128)
    [Lost Points] 1

    |OLD|  Test Description] [#2 - Raw data display] predicted temperature at location z should be closer to known temperature at location x than to known temperature at location y, if z is closer (in distance) to x than y, and vice versa
    [Observed Error] next on empty iterator
    [exception was thrown] detailed error message in debug output section below
    [Lost Points] 10
    
    |OK|  [Test Description] [#2 - Raw data display] predicted temperature at location z should be closer to known temperature at location x than to known temperature at location y, if z is closer (in distance) to x than y, and vice versa
    [Observed Error] -60.0 did not equal 10.0 +- 1.0E-4 Incorrect predicted temperature at Location(90.0,-180.0): -60.0. Expected: 10.0
    [Lost Points] 10
    
    |OK|  [Test Description] [#2 - Raw data display] exceeding the greatest value of a color scale should return the color associated with the greatest value
    [Observed Error] NoSuchElementException was thrown during property evaluation.
      Message: "None"
      Occurred when passed generated values (
        arg0 = 0.0,
        arg1 = -80.19246221066285
      )
    [Lost Points] 2