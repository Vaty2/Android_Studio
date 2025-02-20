package com.mobilei.homework1.data

data class ProductModel(
    var id: Int = 0,
    var title: String = "no title",
    var img: String = "no img",
    var price: Double = 0.0,
    var rate: Double = 0.0,
    var description: String = "",
    var quantity: Int = 1,
    var size: String = "",
    var type: String = "",
)

var productList: List<ProductModel> = listOf(
    ProductModel(
        id = 1,
        title = "ArmChairs",
        img = "https://i.pinimg.com/736x/45/d4/6a/45d46aeb46abd6cad7298b2b202596f8.jpg",
        price = 3.5,
        rate = 4.2,
        size = "75cm",
        description = "An accent chair or wooden armchair with a dash of mid-century style can be just the thing to fill a seating gap, adding style to a space and eliminating the claustrophobia that too many soft seats can create.",
        type = "Chairs",

    ),
    ProductModel(
        id = 2,
        title = "Cabinet",
        img = "https://i.pinimg.com/736x/2c/df/d3/2cdfd38d2ddac153a826043fd85cbed7.jpg",
        price = 4.5,
        rate = 4.0,
        size = "80cm",
        description = "An accent chair or wooden armchair with a dash of mid-century style can be just the thing to fill a seating gap, adding style to a space and eliminating the claustrophobia that too many soft seats can create.",
        type = "Cabinet"
    ),
    ProductModel(
        id = 3,
        title = "Sofa 1",
        img = "https://i.pinimg.com/736x/40/78/20/4078202662d91e6e5496d3f650572a69.jpg",
        price = 2.9,
        rate = 3.9,
        size = "180cm",
        description = "An accent chair or wooden armchair with a dash of mid-century style can be just the thing to fill a seating gap, adding style to a space and eliminating the claustrophobia that too many soft seats can create."
        ,type = "Chairs"
    ),
    ProductModel(
        id = 4,
        title = "Sofa 2",
        img = "https://i.pinimg.com/736x/a4/8b/72/a48b721c2346806ac75224b863658e69.jpg",
        price = 2.5,
        rate = 3.5,
        size = "180cm",
        description = "An accent chair or wooden armchair with a dash of mid-century style can be just the thing to fill a seating gap, adding style to a space and eliminating the claustrophobia that too many soft seats can create."
        ,type = "Sofas"
    ),
    ProductModel(
        id = 5,
        title = "BookCase",
        img = "https://i.pinimg.com/736x/47/31/2b/47312b46b98caf172c77676e21ab3431.jpg",
        price = 6.0,
        rate = 4.8,
        size = "100cm",
        description = "An accent chair or wooden armchair with a dash of mid-century style can be just the thing to fill a seating gap, adding style to a space and eliminating the claustrophobia that too many soft seats can create."
        ,type = "Chest Of Drawers"
    ),
    ProductModel(
        id = 6,
        title = "Range Chair",
        img = "https://i.pinimg.com/736x/94/0e/cb/940ecb862d684587726ef15db97b8e8b.jpg",
        price = 6.0,
        rate = 4.8,
        size = "90cm",
        description = "An accent chair or wooden armchair with a dash of mid-century style can be just the thing to fill a seating gap, adding style to a space and eliminating the claustrophobia that too many soft seats can create."
        ,type = "Chairs"
    ),
    ProductModel(
        id = 7,
        title = "Kendall Velvet Office Chair",
        img = "https://i.pinimg.com/736x/2d/7b/5d/2d7b5d401f1bb3f4f886d3d50a87e69a.jpg",
        price = 6.0,
        rate = 4.8,
        size = "90cm",
        description = "An accent chair or wooden armchair with a dash of mid-century style can be just the thing to fill a seating gap, adding style to a space and eliminating the claustrophobia that too many soft seats can create."
        ,type = "Chairs"
    ),
    ProductModel(
        id = 8,
        title = "Beauty Chairs",
        img = "https://i.pinimg.com/736x/fd/58/50/fd58503cab876604b0fd665d262cc6c2.jpg",
        price = 6.0,
        rate = 4.8,
        size = "90cm",
        description = "An accent chair or wooden armchair with a dash of mid-century style can be just the thing to fill a seating gap, adding style to a space and eliminating the claustrophobia that too many soft seats can create."
        ,type = "Chairs"
    ),
    ProductModel(
        id = 9,
        title = "2 Seater Sofas",
        img = "https://i.pinimg.com/736x/a7/0c/cb/a70ccb35ed6400c1eba8e0b7e57cac83.jpg",
        price = 6.0,
        rate = 4.8,
        size = "150cm",
        description = "An accent chair or wooden armchair with a dash of mid-century style can be just the thing to fill a seating gap, adding style to a space and eliminating the claustrophobia that too many soft seats can create."
        ,type = "Sofas"
    ),
    ProductModel(
        id = 10,
        title = "Moderns Chairs",
        img = "https://i.pinimg.com/736x/27/65/fc/2765fcea7f21ab017511b6ba4ab3daac.jpg",
        price = 6.0,
        rate = 4.8,
        size = "150cm",
        description = "An accent chair or wooden armchair with a dash of mid-century style can be just the thing to fill a seating gap, adding style to a space and eliminating the claustrophobia that too many soft seats can create."
        ,type = "Chairs"
    ),
)


