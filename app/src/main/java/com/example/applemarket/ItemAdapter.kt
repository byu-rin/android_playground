// RecyclerView 에 데이터를 표시하기 위한 `ItemAdapter` 클래스

package com.example.applemarket

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.applemarket.databinding.ItemBinding
import java.text.DecimalFormat

// ItemAdapter 클래스는 RecyclerView.Adapter 클래스를 상속한다.
// RecyclerView와 데이터를 연결하여 리스트 아이템을 관리
// mItems 매개변수는 RecyclerView에 표시할 데이터를 담고 있는 MutableList입니다.
class ItemAdapter(private val mItems: MutableList<SaleItem>) : RecyclerView.Adapter<ItemAdapter.Holder>() {


    // Adapter에서 아이템 클릭 및 롱 클릭 이벤트를 처리하기 위한 인터페이스를 정의
    interface ItemClick {
        fun onClick(view : View, position : Int)
    }

    interface ItemLongClick {
        fun onLongClick(view : View, position : Int)
    }

    var itemClick : ItemClick? = null
    var itemLongClick : ItemLongClick? = null


    // onCreateViewHolder 함수는 ViewHolder 객체를 생성, 초기화
    // ItemBinding.inflate() 함수를 통해 XML 레이아웃 파일에서 뷰를 인플레이트, 그 뷰를 사용하여 Holder 객체를 생성
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        val binding = ItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return Holder(binding)
    }

    // onBindViewHolder 함수는 ViewHolder에 데이터를 바인딩
    // 여기에서 아이템 클릭 및 롱 클릭 이벤트 처리
    // 데이터를 ViewHolder의 뷰에 설정하고, 가격을 포맷팅하고, 관심 상품 아이콘을 설정
    override fun onBindViewHolder(holder: Holder, position: Int) {
        // 아이템 클릭 이벤트 처리
        holder.itemView.setOnClickListener {
            itemClick?.onClick(it, position)
        }
        // 아이템 롱 클릭 이벤트 처리
        holder.itemView.setOnLongClickListener() OnLongClickListener@{
            itemLongClick?.onLongClick(it, position)
            return@OnLongClickListener true
        }

        // 아이템 데이터 설정
        holder.itemImageView.setImageResource(mItems[position].Image)
        holder.tvItemTitle.text = mItems[position].ItemTitle
        holder.tvAddress.text = mItems[position].Address

        // 가격 포맷팅
        val price = mItems[position].Price
        (DecimalFormat("#,###").format(price)+"원").also { holder.tvPrice.text = it }

        // 채팅 및 관심 횟수 설정
        holder.tvItemChat.text = mItems[position].ChatCnt.toString()
        holder.tvItemLike.text = mItems[position].InterestCnt.toString()

        // 관심 상품 아이콘 설정
        if(mItems[position].isLike)
            holder.ivAdapterLike.setImageResource(R.drawable.fill_like)
        else
            holder.ivAdapterLike.setImageResource(R.drawable.like)
    }

    // getItemId 함수는 아이템의 ID를 반환
    // 여기서는 아이템의 위치(position)를 그대로 ID로 사용한다.
    // getItemCount 함수는 RecyclerView에 표시할 아이템 개수 반환
    // mItems.size 통해 데이터 리스트 크기 반환
    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getItemCount(): Int {
        return mItems.size
    }

    // Holder 클래스는 ViewHolder 패턴을 구현
    // XML 레이아웃에서 정의한 뷰들을 멤버 변수로 가진다.
    // 이 뷰들에 아이템 데이터 설정, 아이템 이벤트를 처리
    inner class Holder(binding: ItemBinding) : RecyclerView.ViewHolder(binding.root) {
        val itemImageView = binding.iconItem
        val tvItemTitle = binding.tvItemTitle
        val tvAddress = binding.tvAddress
        val tvPrice = binding.tvPrice
        val tvItemChat = binding.tvChatCnt
        val tvItemLike = binding.tvLikecnt
        val ivAdapterLike = binding.ivLike
    }
}