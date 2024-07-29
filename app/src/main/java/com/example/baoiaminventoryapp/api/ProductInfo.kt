package com.example.baoiaminventoryapp.api

data class ProductResponse (
    val products: List<Product>
)

data class Product(
    val barcode_number: String,
    val barcode_formats: String,
    val images: List<String>,

    val title: String,
    val model: String,
    val description: String,
    val brand: String,

    val weight: String,
    val length: String,
    val height: String,
    val width: String,

    val asin: String,
    val manufacturer: String,
)

// JSON response from barcode api format:
/*
{
    "products": [
        {
            "barcode_number": "886736874135",
            "barcode_formats": "UPC-A 886736874135, EAN-13 0886736874135",
            "mpn": "CE-XLR3200",
            "model": "XLR",
            "asin": "B01KUHG2G8",
            "title": "Nike Red Running Shoes - Size 10",
            "category": "Media > Books > Print Books",
            "manufacturer": "Xerox",
            "brand": "Xerox",
            "contributors": [
                {
                    "role": "author",
                    "name": "Blake, Quentin"
                },
                {
                    "role": "publisher",
                    "name": "Greenwillow Books"
                }
            ],
            "age_group": "adult",
            "ingredients": "Organic Tapioca Syrup, Organic Dried Cane Syrup, Natural Flavor.",
            "nutrition_facts": "Protein 0 G, Total lipid (fat) 0 G, Energy 300 KCAL, Sugars, total including NLEA 40 G.",
            "energy_efficiency_class": "A+ (A+++ to D)",
            "color": "blue",
            "gender": "female",
            "material": "cloth",
            "pattern": "checkered",
            "format": "DVD",
            "multipack": "8",
            "size": "7 US",
            "length": "45 in",
            "width": "30 in",
            "height": "22 in",
            "weight": "7 lb",
            "release_date": "2003-07-28",
            "description": "One of a kind, Nike Red Running Shoes that are great for walking, running and sports.",
            "features": [
                "Rugged construction",
                "Convenient carrying case",
                "5 year warranty"
            ],
            "images": [
                "https://images.barcodelookup.com/5219/52194594-1.jpg",
                "https://images.barcodelookup.com/5219/52194594-2.jpg",
                "https://images.barcodelookup.com/5219/52194594-3.jpg"
            ],
            "last_update": "2022-03-03 20:28:19",
            "stores": [
                {
                    "name": "Newegg.com",
                    "country": "US",
                    "currency": "USD",
                    "currency_symbol": "$",
                    "price": "41.38",
                    "sale_price": "35.99",
                    "tax": [
                        {
                            "country": "US",
                            "region": "US",
                            "rate": "5.00",
                            "tax_ship": "no"
                        }
                    ],
                    "link": "https://www.newegg.com/product-link",
                    "item_group_id": "AB-4312",
                    "availability": "in stock",
                    "condition": "new",
                    "shipping": [
                        {
                            "country": "US",
                            "region": "US",
                            "service": "Standard",
                            "price": "8.49 USD"
                        }
                    ],
                    "last_update": "2021-05-19 09:07:42"
                },
                .....,
                .....
            ],
            "reviews": [
                {
                    "name": "Josh Keller",
                    "rating": "5",
                    "title": "Love these shoes!",
                    "review": "A stylish and great fitting shoe for walking and running.",
                    "date": "2015-03-19 21:48:03"
                },
                .....,
                .....
            ]
        }
    ]
}

 */