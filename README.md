# NamedCounter
This  is a WEB API Which allows user to create a counter , increment the same and also retrieve the list of counter
Basic Testing

https://namedcounter.herokuapp.com/NamedCounterAPI/

The below requests are to create a named counter. any number of counters can be created. Counters will be intialized to value Zero
Request format  https://namedcounter.herokuapp.com/NamedCounterAPI/insert/{nameofthecounter1}
For example:
  [PUT] https://namedcounter.herokuapp.com/NamedCounterAPI/insert/counter1
  [PUT] https://namedcounter.herokuapp.com/NamedCounterAPI/insert/counter2
  [PUT] https://namedcounter.herokuapp.com/NamedCounterAPI/insert/counter3

The below requests are to increment the value of counter by 1
  [GET] https://namedcounter.herokuapp.com/NamedCounterAPI/incrementValue/counter1
  [GET] https://namedcounter.herokuapp.com/NamedCounterAPI/incrementValue/counter2
  [GET] https://namedcounter.herokuapp.com/NamedCounterAPI/incrementValue/counter3


The below requests are to get the present value of any counter
  [GET] https://namedcounter.herokuapp.com/NamedCounterAPI/getValue/counter1
  [GET] https://namedcounter.herokuapp.com/NamedCounterAPI/getValue/counter2
  [GET] https://namedcounter.herokuapp.com/NamedCounterAPI/getValue/counter3

The below URL will get the list of the namedcounters along with the value
  [GET] https://namedcounter.herokuapp.com/NamedCounterAPI/getList


Load Testing in Multithreaded Environment

I used website https://loadimpact.com/ to load test the api in multi threaded environment simumating real user.

Opened three different tabs . in two tabs incrementing the value of one counter ( in parallel) and in third tab accessing the list , also accessing the value of the named counter

Test case: 25 virtual users create requests in parallel. At the end of the test, the value of the counter should be equal to the sum of requests sent. 


