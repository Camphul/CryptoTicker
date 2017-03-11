# PriceTicker
PriceTicker is a Spring-boot application that updates crypto currency prices every so often.
It makes use of Spring-boot, hibernate(to store price history), thymeleaf, angularjs(for refreshing and front end), bootstrap.

This is in no way a professional spring-boot application. I'm currently learning about the framework by making applications like these.

## Current supported currencies and markets
This code currently works with the Bitcoin currency and has the market prices from Coindesk, Coinbase and Bitstamp
All prices are using the USD. 

## Routes
###/currencies
Returns a list of usable currency strings
###/{currency}/average
Average price from all markets together
###/{currency}/markets
Return a list of usable markets for the given currency
###/{currency}/{markets}/price
Return average currency worth of the given markets separated by a comma.

###/{currency}/all/price
Return average currency worth of all available markets.

## Todo
- Nice looking front end
- Use models instead of returning strings in mappings **Pretty much done**
- General code improvements
- Configurable delays etc....
- Currency converter(allow euro etc..)

## License
The license for this project can be found under LICENSE.txt
