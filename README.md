# API Finance Data Provider

##### API Google Finance
+ symbol lookup
+ historical quotes

##### API Yahoo Finance
+ symbol lookup

##### API Simulation
+ historical quotes

# Get Started
```java
APIManager locAPIManager = APIManager.FactoryGetInstance(API.GOOGLE_FINANCE);
HistoricalQuotes locHistoricalQuotes = locAPIManager.getHistoricalQuotes("GOOGL", TimePeriod.YEAR_1, null);
System.out.println(locHistoricalQuotes.toString());
```
