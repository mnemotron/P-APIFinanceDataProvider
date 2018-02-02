# API Finance Data Provider

##### API Google Finance
+ symbol lookup
+ historical quotes

##### API Yahoo Finance
+ symbol lookup

##### API Simulation
+ historical quotes

# Get Started
## Symbol Lookup
```java
// create API manager instance i.e. to get Google Finance ticker IDs
APIManager locAPIManager = APIManager.FactoryGetInstance(API.GOOGLE_FINANCE);

// symbol lookup with query string "G"
Tickers locTickers = locAPIManager.searchTicker("G");

// print result
System.out.println(locTickers.toString());
```
Result:
```
query:G
{ tickerID:GOOGL, tickerName:Alphabet Inc }
{ tickerID:GE, tickerName:General Electric Company }
{ tickerID:GLD, tickerName:SPDR Gold Trust (ETF) }
...
```



## Historical Data
```java
// create API manager instance i.e. to get Google Finance data
APIManager locAPIManager = APIManager.FactoryGetInstance(API.GOOGLE_FINANCE);

// get historical data with ticker ID: "GOOGL", time period: 1 year, interval: not yet supported
HistoricalQuotes locHistoricalQuotes = locAPIManager.getHistoricalQuotes("GOOGL", TimePeriod.YEAR_1, null);

// print result
System.out.println(locHistoricalQuotes.toString());
```
