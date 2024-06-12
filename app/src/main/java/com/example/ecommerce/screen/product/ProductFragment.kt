package com.example.ecommerce.screen.product

import android.os.Bundle
import android.text.Html
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.example.ecommerce.R
import com.example.ecommerce.databinding.FragmentProductBinding
import com.example.ecommerce.screen.cart.ShoppingCartViewModel
import com.example.ecommerce.utils.ConnectivityUtil
import com.example.ecommerce.utils.Constants
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ProductFragment() : Fragment(R.layout.fragment_product) {

    private lateinit var binding: FragmentProductBinding

    private val productViewModel: ProductViewModel by viewModels()
    private val cartViewModel: ShoppingCartViewModel by viewModels()

    private val args: ProductFragmentArgs by navArgs()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initObserver()
    }

    private fun initObserver() {
        productViewModel.productDetails.observe(this) {
            binding.productName.text = it.Data.Name
            Glide.with(binding.productImage.context)
                .load(it.Data.PictureModels[0].ImageUrl)
                .into(binding.productImage)
            binding.productSubTitle.text =
                Html.fromHtml(it.Data.ShortDescription ?: "", Html.FROM_HTML_MODE_COMPACT)
                    .toString()

            binding.descriptionTv.text =
                Html.fromHtml(it.Data.FullDescription ?: "", Html.FROM_HTML_MODE_COMPACT).toString()

            binding.discountPrice.text = it.Data.ProductPrice.Price
            binding.stockTv.text = it.Data.StockAvailability

            if (it.Data.ProductPrice.OldPrice == null) {
                binding.actualPrice.visibility = View.GONE
            } else {
                binding.actualPrice.text = it.Data.ProductPrice.OldPrice.toString()
            }

            binding.shimmerLayout.stopShimmer()
            binding.scrollView.visibility = View.VISIBLE
            binding.shimmerLayout.visibility = View.GONE
        }
        productViewModel.cartResponse.observe(this) {
            Toast.makeText(requireContext(), it.Message, Toast.LENGTH_SHORT).show()
            loadCartItemCount()
        }
        productViewModel.showMessage.observe(this){ message->
            Toast.makeText(requireContext(), message, Toast.LENGTH_SHORT).show()
        }
    }

    private fun loadCartItemCount() {
        cartViewModel.fetchCartProducts()
        cartViewModel.items.observe(viewLifecycleOwner) { products ->
            Constants.currCartItem = products.Data.Cart.Items.size
            binding.cartItem.text = Constants.currCartItem.toString()
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        super.onViewCreated(view, savedInstanceState)
        binding = FragmentProductBinding.bind(view)

        binding.scrollView.visibility = View.INVISIBLE
        binding.shimmerLayout.startShimmer()

        loadData()

        binding.cartItem.text = Constants.currCartItem.toString()

        binding.tollBar.setNavigationOnClickListener {
            findNavController().popBackStack()
        }
        binding.addBtn.setOnClickListener {
            binding.quantityTb.text = (binding.quantityTb.text.toString().toInt() + 1).toString()
        }
        binding.removeBtn.setOnClickListener {
            if (binding.quantityTb.text.toString().toInt() > 1) {
                binding.quantityTb.text =
                    (binding.quantityTb.text.toString().toInt() - 1).toString()
            }
        }

        binding.btnAddToCart.setOnClickListener {
            if (ConnectivityUtil.isNetworkAvailable(requireContext())) {
                productViewModel.addToCart(
                    args.productID,
                    binding.quantityTb.text.toString().toInt()
                )
                loadCartItemCount()
            }
            else{
                Toast.makeText(requireContext(), "Check you internet connection!!", Toast.LENGTH_SHORT).show()
            }
        }

        binding.iconCart.setOnClickListener {
            val action = ProductFragmentDirections.actionProductFragmentToShoppingCartFragment()
            findNavController().navigate(action)
        }
    }

    private fun loadData() {
        productViewModel.fetchProductDeatils(args.productID)
    }

}