db = db.getSiblingDB('xprice');

db.siteDetails.insertMany([
    {
        name: "Mediamarkt",
        baseUrl: "https://www.mediamarkt.com.tr",
        searchUrl: "https://www.mediamarkt.com.tr/tr/category/macbook-air-645070.html?filter=processorModel:M2&sort=currentprice+asc",
        elementPath: "div[data-test=mms-product-card]",
        productNamePathOfElement: "p[data-test=product-title]",
        pricePathOfElement: "span.sc-8b815c14-0.hIJQGE.sc-dd1a61d2-2.efAprc",
        urlPathOfElement: "a[data-test=mms-product-list-item-link]"
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
        baseUrl: "https://www.pazarama.com",
        searchUrl: "https://www.pazarama.com/arama?kategori=dizustu-bilgisayar-k-K04078&q=MacBook%20Air%20M2&siralama=artan-fiyat",
        elementPath: ".product-card.bg-white.relative",
        productNamePathOfElement: "a[title]",
        pricePathOfElement: ".leading-tight.text-blue-500.font-semibold.text-huge",
        urlPathOfElement: "a[href]"
    }
]);