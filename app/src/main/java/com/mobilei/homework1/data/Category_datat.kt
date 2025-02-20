package com.mobilei.homework1.data

data class CategoryModel(
    var id: Int = 0,
    var image: String = "",
    var name: String = ""
)

var categoryList: List<CategoryModel> = listOf(
    CategoryModel(
        id = 1,
        image = "https://i.pinimg.com/736x/50/e5/ef/50e5efb600db7a38ed30bd687a6e15d2.jpg",
        name = "Chairs"
    ),
    CategoryModel(
        id = 2,
        image = "https://i.pinimg.com/736x/cd/1a/6e/cd1a6ee8f47f3d65564590341d232a2a.jpg",
        name = "Sofas"
    ),
    CategoryModel(
        id = 3,
        image = "https://i.pinimg.com/736x/a6/2d/ff/a62dff9f4522df886cb6c27c3d8bee06.jpg",
        name = "Cabinet"
    ),
    CategoryModel(
        id = 4,
        image = "https://i.pinimg.com/736x/67/9a/5e/679a5e040516bfa0f28db5079bf459f1.jpg",
        name = "Dining Chairs"
    ),
    CategoryModel(
        id = 5,
        image = "https://i.pinimg.com/736x/6f/e8/72/6fe872b6582708003e281ca1603a359d.jpg",
        name = "Chest Of Drawers"
    ),
    CategoryModel(
        id = 6,
        image = "https://i.pinimg.com/736x/49/14/ac/4914ac8f783f070146a7f2edfc82d208.jpg",
        name = "Bed"
    ),
    CategoryModel(
        id = 7,
        image = "https://i.pinimg.com/736x/b4/c4/20/b4c420f3d289b8f7c71c763cc25de8a9.jpg",
        name = "Striped Pattern"
    ),
    CategoryModel(
        id = 8,
        image = "https://i.pinimg.com/736x/cc/5c/93/cc5c93f552d13040d2b27f123b3439a4.jpg",
        name = "Lams"
    )
)