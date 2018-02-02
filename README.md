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
Result:
```
tickerID:GOOGL
interval:null
from:2017-02-02T20:57:11.425Z
to:2018-02-02T20:57:11.445Z
{ date:2018-01-31T23:00:00Z, close:1181.59, open:1175.99, low:1169.36, high:1187.45, volume:3675709 }
{ date:2018-01-30T23:00:00Z, close:1182.22, open:1183.81, low:1172.1, high:1186.32, volume:1801135 }
{ date:2018-01-29T23:00:00Z, close:1177.37, open:1177.72, low:1174.51, high:1187.93, volume:1866883 }
{ date:2018-01-28T23:00:00Z, close:1186.48, open:1188.0, low:1184.06, high:1198.0, volume:1574708 }
{ date:2018-01-25T23:00:00Z, close:1187.56, open:1187.53, low:1168.03, high:1187.56, volume:2108502 }
...
```
