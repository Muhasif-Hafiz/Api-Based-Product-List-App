import android.telecom.Call
import retrofit2.http.GET

interface ApiInterface {


     @GET("products")
    fun getProducts() : retrofit2.Call<MyData>
}