package com.shaadicom.main

import android.graphics.Color
import android.text.Html
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.shaadicom.R
import com.shaadicom.database.User
import com.shaadicom.database.UserViewModel
import com.squareup.picasso.Picasso

class UsersRecyclerView(val ViewModel2: UserViewModel) : RecyclerView.Adapter<UsersRecyclerView.ViewHolder>() {

    private var userList: List<User> = emptyList()

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        //initialize the views

        val MatchedUserPic = itemView.findViewById<ImageView>(R.id.matched_user_photo_requests)
        val UserName= itemView.findViewById<TextView>(R.id.matched_user_name_requests)
        val user_details= itemView.findViewById<TextView>(R.id.matched_details_requests)
        val AcceptMatch =itemView.findViewById<Button>(R.id.accept_button)
        val DeclineMatch = itemView.findViewById<Button>(R.id.decline_button)
        val accepted_user = itemView.findViewById<TextView>(R.id.acccepted_declined)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.users_recycler_view, parent, false )
        return ViewHolder(v)
    }

    override fun getItemCount(): Int {
        return userList.size
    }



    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        holder.setIsRecyclable(false)

        val details = userList[position]
        holder.run {
            UserName.text = details.name.title + " " +details.name.first + " "+details.name.last
        }

        if (details.picture.large != ""){
            Picasso.get()
                .load(details.picture.large )
                .resize(150,150)
                .centerCrop()
                .placeholder(R.drawable.profile_pic_placeholder)
                .transform(CircleTransform())
                .into(holder.MatchedUserPic)
        }

        var age = "NA"
        if(details.dob.age.toString().isNotEmpty()) {
            age = details.dob.age.toString().replace(".0","")+ " yrs"
        }

        var gender = "NA"
        var city = "NA"
        var state = "NA"
        var country = "NA"



        if (details.gender != null) {
            gender = (details.gender.toString())!!
        }
        if (details.location.state != null) {
            state = (details.location.state.toString())!!
        }
        if (details.location.city != null){
            city = details.location.city?.toString()
        }
        if (details.location.country != null){
            country = details.location.country?.toString()
        }

        val text: String =
            java.lang.String.format(
                "%s, %s <br /> %s, %s, %s",
                age,
                gender,
                city,
                state,
                country
            )
        holder.run {
            user_details.setText(Html.fromHtml(text), TextView.BufferType.SPANNABLE)
        }

        if (details.status==0){
            holder.run {
                accepted_user.setText("Member accepted")
                accepted_user.setTextColor(Color.parseColor("#1B9638"))
                accepted_user.visibility = View.GONE
                AcceptMatch.visibility= View.VISIBLE
                DeclineMatch.visibility= View.VISIBLE
            }
        }

        if (details.status==1){
            holder.run {
                accepted_user.setText("Member accepted")
                accepted_user.setTextColor(Color.parseColor("#1B9638"))
                accepted_user.visibility = View.VISIBLE
                AcceptMatch.visibility= View.GONE
                DeclineMatch.visibility= View.GONE
            }
        }

        if (details.status==2){

            holder.run {
                accepted_user.setText("Member declined")
                accepted_user.setTextColor(Color.parseColor("#F51010"))
                accepted_user.visibility = View.VISIBLE
                AcceptMatch.visibility= View.GONE
                DeclineMatch.visibility= View.GONE
            }
        }

        holder.AcceptMatch.setOnClickListener{
            val user = User(details.user_id,details.cell,details.dob,details.email,
            details.gender,details.location,details.name,details.nat,details.phone,details.picture,1)
            ViewModel2.addUser(user)
            holder.run {

                accepted_user.setText("Member accepted")
                accepted_user.setTextColor(Color.parseColor("#1B9638"))
                accepted_user.visibility = View.VISIBLE
                AcceptMatch.visibility= View.GONE
                DeclineMatch.visibility= View.GONE
            }

        }
        holder.DeclineMatch.setOnClickListener{
            val user = User(details.user_id,details.cell,details.dob,details.email,
                details.gender,details.location,details.name,details.nat,details.phone,details.picture,2)
            ViewModel2.addUser(user)
            holder.run {
                accepted_user.setText("Member declined")
                accepted_user.setTextColor(Color.parseColor("#F51010"))
                accepted_user.visibility = View.VISIBLE
                AcceptMatch.visibility= View.GONE
                DeclineMatch.visibility= View.GONE
            }
        }
    }
    fun setData(user: List<User>){
        this.userList = user
        notifyDataSetChanged()
    }

}