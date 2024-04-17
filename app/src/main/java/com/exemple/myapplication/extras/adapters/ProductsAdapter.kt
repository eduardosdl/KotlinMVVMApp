package com.exemple.myapplication.extras.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.exemple.myapplication.Product
import com.exemple.myapplication.databinding.ItemProductBinding

// classe que "cria" a lista de produtos
class ProductsAdapter(private val onItemClicked: (Product) -> Unit): RecyclerView.Adapter<ProductsViewHolder>() {
    private var products = mutableListOf<Product>()

    fun setProductsList(products: List<Product>) {
        this.products = products.toMutableList()
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductsViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemProductBinding.inflate(inflater, parent, false)
        return ProductsViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ProductsViewHolder, position: Int) {
        val product = products[position]
        holder.bind(product, onItemClicked)
    }

    override fun getItemCount(): Int {
        return products.size
    }
}

    class ProductsViewHolder(val binding: ItemProductBinding): RecyclerView.ViewHolder(binding.root) {
        fun bind(product: Product, onItemClicked: (Product) -> Unit) {

            binding.productName.text = product.title
            binding.productDescription.text = product.description
            binding.productPrice.text = product.price.toString()

            itemView.setOnClickListener {
                onItemClicked(product)
            }
        }
}