db = db.getSiblingDB('xprice');

db.siteDetails.insertMany([
    {
        name: "Mediamarkt",
        baseUrl: "https://www.trendyol.com",
        searchUrl: "https://www.trendyol.com/sr?q=macbook%20air%20m2&qt=MacBook%20Air%20M2&st=MacBook%20Air%20M2&sst=PRICE_BY_ASC&lc=103108&os=1",
        elementPath: "ul.non-style > li > div#product-item",
        productNamePathOfElement: "data-product-name",
        pricePathOfElement: "data-price-with-discount",
        urlPathOfElement: "data-product-url"
    },
    {
        name: "Teknosa",
        baseUrl: "https://www.teknosa.com",
        searchUrl: "https://www.teknosa.com/arama?s=MacBook+Air+M2+%3Aprice-asc%3Acategory%3A116%3Acategory%3A116004%3Aislemci_116-CLS-2500%3AApple%2BM2%2B%2B&text=MacBook+Air+M2+",
        elementPath: "ul.non-style > li > div#product-item",
        productNamePathOfElement: "data-product-name",
        pricePathOfElement: "data-price-with-discount",
        urlPathOfElement: "a.prd-link"
    },
    {
        name: "Pazarama",
        baseUrl: "https://www.hepsiburada.com",
        searchUrl: "https://www.hepsiburada.com/ara?q=macbook+air+m2",
        elementPath: "div.product-item",
        productNamePathOfElement: "data-product-name",
        pricePathOfElement: "data-price",
        urlPathOfElement: "a.product-link"
    }
]);