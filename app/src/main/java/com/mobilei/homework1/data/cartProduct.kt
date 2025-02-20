import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue

data class CartProductModel(
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

// ✅ Make cart list mutable & observable
var cartList by mutableStateOf<List<CartProductModel>>(emptyList())

fun addToCart(product: CartProductModel) {
    val existingProduct = cartList.find { it.id == product.id }
    cartList = if (existingProduct != null) {
        cartList.map {
            if (it.id == product.id) it.copy(quantity = it.quantity + 1) else it
        }
    } else {
        cartList + product.copy(quantity = 1)
    }
}