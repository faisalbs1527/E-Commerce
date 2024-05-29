package com.example.ecommerce.screen.product

import android.os.Bundle
import android.text.Html
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.example.ecommerce.R
import com.example.ecommerce.databinding.FragmentProductBinding
import com.example.ecommerce.screen.home.HomeFragment
import com.example.ecommerce.model.productDao
import com.example.ecommerce.screen.home.HomeFragmentDirections
import com.example.ecommerce.utils.Constants

class ProductFragment() : Fragment(R.layout.fragment_product) {

    private lateinit var binding : FragmentProductBinding

    private val productViewModel : ProductViewModel by viewModels()

    private val args:ProductFragmentArgs by navArgs()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initObserver()
    }

    private fun initObserver(){
        productViewModel.productDetails.observe(this){
            binding.productName.text = it.Data.Name
            Glide.with(binding.productImage.context)
                .load(it.Data.PictureModels[0].ImageUrl)
                .into(binding.productImage)
            binding.productSubTitle.text=
                Html.fromHtml(it.Data.ShortDescription?:"",Html.FROM_HTML_MODE_COMPACT).toString()

            binding.descriptionTv.text=
                Html.fromHtml(it.Data.FullDescription?:"",Html.FROM_HTML_MODE_COMPACT).toString()

            binding.discountPrice.text=it.Data.ProductPrice.Price
            binding.stockTv.text= it.Data.StockAvailability

            if(it.Data.ProductPrice.OldPrice==null){
                binding.actualPrice.visibility = View.GONE
            }
            else{
                binding.actualPrice.text = it.Data.ProductPrice.OldPrice.toString()
            }
        }
        productViewModel.cartResponse.observe(this){
            Toast.makeText(requireContext(),it.Message,Toast.LENGTH_SHORT).show()
            loadCartItemCount()
        }
    }

    private fun loadCartItemCount(){
        binding.cartItem.text = Constants.currCartItem.toString()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        binding = FragmentProductBinding.bind(view)
        super.onViewCreated(view, savedInstanceState)

        loadData()

        loadCartItemCount()

        binding.tollBar.setNavigationOnClickListener {
            findNavController().popBackStack()
//            parentFragmentManager.beginTransaction().replace(R.id.fragment_part,HomeFragment()).commit()
        }
        binding.addBtn.setOnClickListener {
            binding.quantityTb.text = (binding.quantityTb.text.toString().toInt() + 1).toString()
        }
        binding.removeBtn.setOnClickListener {
            if(binding.quantityTb.text.toString().toInt()>1){
                binding.quantityTb.text = (binding.quantityTb.text.toString().toInt() - 1).toString()
            }
        }

        binding.btnAddToCart.setOnClickListener {
            productViewModel.addToCart(args.productID,binding.quantityTb.text.toString().toInt())
        }

        binding.iconCart.setOnClickListener{
            val action = ProductFragmentDirections.actionProductFragmentToShoppingCartFragment()
            findNavController().navigate(action)
        }
    }

    private fun loadData(){
        productViewModel.fetchProductDeatils(args.productID)
    }

}