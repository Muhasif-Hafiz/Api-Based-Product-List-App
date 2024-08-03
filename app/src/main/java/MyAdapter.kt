import android.app.Activity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.muhasib.onlineproducts.R
import com.squareup.picasso.Picasso

class MyAdapter(var context : Activity, var productList : List<Product>) :
RecyclerView.Adapter<MyAdapter.MyViewHolder>()

{


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {

        val itemView =LayoutInflater.from(context).inflate(R.layout.each_row,parent,false)

        return MyViewHolder(itemView)
    }

    override fun getItemCount(): Int {
        return productList.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {

        val currentItem= productList[position]

        holder.name.text=currentItem.title
        holder.description.text=currentItem.description
        Picasso.get().load(currentItem.thumbnail).into(holder.image)
        holder.off.text=currentItem.discountPercentage.toString()+" %off"

    }
    class MyViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView) {


        val name :TextView
        val image :ImageView
        val description : TextView
        val off : TextView

        init {
            name=itemView.findViewById(R.id.ProductName)
            description=itemView.findViewById(R.id.ProductDescription)
            image=itemView.findViewById(R.id.ProductImage)
            off=itemView.findViewById(R.id.ProductOff)
        }
    }


}