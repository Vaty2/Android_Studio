package com.mobilei.homework1.ui.theme.cart

import CartProductModel
import androidx.lifecycle.ViewModel
import androidx.compose.runtime.mutableStateListOf

class CartViewModel : ViewModel() {
    private val _cartItems = mutableStateListOf<CartProductModel>()
    val cartItems: List<CartProductModel> get() = _cartItems

    fun addToCart(product: CartProductModel) {
        // Check if item already exists in the cart
        val existingItem = _cartItems.find { it.id == product.id }
        if (existingItem != null) {
            // Remove the old item and add an updated one to trigger recomposition
            _cartItems.remove(existingItem)
            _cartItems.add(existingItem.copy(quantity = existingItem.quantity + 1))
        } else {
            _cartItems.add(product.copy(quantity = 1)) // Add new item with quantity 1
        }
    }

    fun removeFromCart(product: CartProductModel) {
        // Remove the item based on the ID to avoid object reference mismatch issues
        _cartItems.removeAll { it.id == product.id }
    }
}
