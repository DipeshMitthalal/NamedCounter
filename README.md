# NamedCounter
This  is a WEB API Which allows user to create a counter , increment the same and also retrieve the list of counter

Heroku Link: https://namedcounter.herokuapp.com/NamedCounterAPI/
V 0.1: initial version
V 1.0: With better response codes and messages

Requests to create a named counter. 
Any number of counters can be created. Counters will be intialized to value Zero
Request format [PUT] https://namedcounter.herokuapp.com/NamedCounterAPI/insert/{nameofthecounter1}
  Examples: 
  https://namedcounter.herokuapp.com/NamedCounterAPI/insert/counter1
  https://namedcounter.herokuapp.com/NamedCounterAPI/insert/counter2
  https://namedcounter.herokuapp.com/NamedCounterAPI/insert/counter3


Requests to increment the value of counter by 1
Request format [GET] https://namedcounter.herokuapp.com/NamedCounterAPI/incrementValue/{nameofthecounter1}
  Examples: 
  https://namedcounter.herokuapp.com/NamedCounterAPI/incrementValue/counter1
  https://namedcounter.herokuapp.com/NamedCounterAPI/incrementValue/counter2
  https://namedcounter.herokuapp.com/NamedCounterAPI/incrementValue/counter3


Requests to get the present value of any counter
Request format [GET] https://namedcounter.herokuapp.com/NamedCounterAPI/getValue/{nameofthecounter1}
  Examples:   
  https://namedcounter.herokuapp.com/NamedCounterAPI/getValue/counter1
  https://namedcounter.herokuapp.com/NamedCounterAPI/getValue/counter2
  https://namedcounter.herokuapp.com/NamedCounterAPI/getValue/counter3

The below URL will get the list of the namedcounters along with the value

  [GET] https://namedcounter.herokuapp.com/NamedCounterAPI/getList



Load Testing in Multithreaded Environment:

I used website https://loadimpact.com/ to load test the api in multi threaded environment simulating the real users.

Opened three different tabs . in two tabs incrementing the value of one counter ( in parallel) and in third tab accessing the list , also accessing the value of the named counter

Test case: 25 virtual users create requests in parallel. At the end of the test, the value of the counter should be equal to the sum of requests sent. 

The source code also contains a small junit test scenario just to check if all the urls are working.

Note: Error messages for negative cases not implemented. i.e if you try to access a counter which is not present, API will always return its  value as zero. If you also try to increment the value of the counter which is not present, it will always return zero. In code necessary check is in place. It is just that return response has to be tweaked

The Above note is revised and implemented in V 1.0
