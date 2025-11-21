So I grabbed Apple's stock prices from yesterday and put them in an array called stockPrices, where:

The indices are the time (in minutes) past trade opening time, which was 9:30am local time.
The values are the price (in US dollars) of one share of Apple stock at that time.
So if the stock cost $500 at 10:30am, that means stockPrices[60] = 500.

Write an efficient method that takes stockPrices and returns the best profit I could have made from one purchase and one sale of one share of Apple stock yesterday.

For example:

```C#
int[] stockPrices = { 10, 7, 5, 8, 11, 9 };

// Returns 6 (buying for $5 and selling for $11)
GetMaxProfit(stockPrices)
```
