package com.example.applemarket

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

// 앱에서 판매되는 상품 정보 표현하기 위한 클래스
// 이 클래스는 Parcelable 인터페이스를 구현하여 객체를 안전하게 다른 액티비티로 전달
// 각각 상품의 이미지, 제목, 상세 정보, 판매자 이름, 가격, 주소, 관심 횟수, 채팅 횟수, 관심 상품 여부
// RecyclerView의 아이템으로 사용, 앱 내 상품 정보를 관리하고 전달

// @Parcelize를 사용하면 객체를 Intent를 통해 다른 액티비티로 전달하거나, 프래그먼트 간에 전달 시 객체의 상태를 보존하고 다시 구성할 수 있다.
// Parcelable은 객체를 직렬화하고 역직렬화하기 위한 인터페이스, 객체를 바이트 스트림으로 변환하고 다시 원래의 객체로 복원
// 객체의 직렬화와 역직렬화는 주로 Intent를 통해 데이터를 다른 액티비티로 전달하거나, 프래그먼트 간 데이터 공유 시 사용
@Parcelize
data class SaleItem(
    val Image: Int,
    val ItemTitle: String,
    val ItemDetail: String,
    val SellerName: String,
    val Price: Int,
    val Address: String,
    var InterestCnt: Int,
    val ChatCnt: Int,
    var isLike: Boolean
)  : Parcelable
