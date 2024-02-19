package com.example.applemarket

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.core.content.res.ResourcesCompat
import com.example.applemarket.databinding.ActivityDetailBinding
import com.google.android.material.snackbar.Snackbar
import java.text.DecimalFormat

class DetailActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDetailBinding

    // 관심 상품 여부 나타내는 변수
    private var isLike = false

    // SaleItem 객체를 가져오기 위한 지연 초기화 프로퍼티
    private val item: SaleItem? by lazy {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            intent.getParcelableExtra(Constants.ITEM_OBJECT, SaleItem::class.java)
        } else {
            val parcelableExtra = intent.getParcelableExtra<SaleItem>(Constants.ITEM_OBJECT)
            parcelableExtra
        }
    }

    // SaleItem 객체의 위치를 저장하는 변수
    private val itemPosition: Int by lazy {
        intent.getIntExtra(Constants.ITEM_INDEX,0)
    }

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Logcat에 디버그 로그 출력
        Log.d("simon","itemPosition = $itemPosition")

        // SaleItem 객체의 이미지를 ImageView에 설정
        binding.ivItemImage.setImageDrawable(item?.let {
            ResourcesCompat.getDrawable(
                resources,
                it.Image,
                null
            )
        })

        // 판매자 이름과 주소, 상품 제목, 상품 설명, 가격 설정
        binding.tvSellerName.text = item?.SellerName
        binding.tvSellerAddress.text = item?.Address
        binding.tvITemTitle.text = item?.ItemTitle
        binding.tvITemDetail.text = item?.ItemDetail
        binding.tvITemDetailPrice.text = DecimalFormat("#,###").format(item?.Price) + "원"

        // 관심 상품 여부 설정
        isLike = item?.isLike == true

        // 관심 상품 이미지 설정
        binding.ivDetailLike.setImageResource(if (isLike) {R.drawable.fill_like }else{R.drawable.like})

        // 뒤로 가기 버튼 클릭 시, exit() 함수 호출
        binding.ivBack.setOnClickListener {
            exit()
        }

        // 관심 상품 추가 레이아웃 클릭 시 동작
        binding.llDetailLike.setOnClickListener {
            if(!isLike){
                // 관심 상품이 아닐 때, 관심 상품으로 설정하고 메시지 표시
                binding.ivDetailLike.setImageResource(R.drawable.fill_like)
                Snackbar.make(binding.constLayout, "관심 목록에 추가되었습니다.", Snackbar.LENGTH_SHORT).show()
                isLike = true
            }else {
                // 이미 관심 상품일 때, 관심 상품 해제
                binding.ivDetailLike.setImageResource(R.drawable.like)
                isLike = false
            }
        }
    }

    // 액티비티 종료 시 호출되는 함수
    fun exit() {
        // MainActivity로 돌아가기 위한 Intent 생성 및 데이터 전달
        val intent = Intent(this, MainActivity::class.java).apply {
            putExtra("itemIndex", itemPosition)
            putExtra("isLike", isLike)
        }
        setResult(RESULT_OK, intent)
        if (!isFinishing) finish() // 액티비티 종료
    }

    // 뒤로 가기 버튼 클릭 시, exit() 함수 호출

    @Deprecated("Deprecated in Java")
    override fun onBackPressed() {
        exit()
    }
}