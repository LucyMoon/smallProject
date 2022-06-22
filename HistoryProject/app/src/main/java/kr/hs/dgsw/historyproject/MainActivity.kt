package kr.hs.dgsw.historyproject

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.os.Message
import android.view.View
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.ViewPager2
import kr.hs.dgsw.historyproject.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity(), View.OnClickListener {
    private lateinit var binding: ActivityMainBinding

    private var bannerPosition = Int.MAX_VALUE / 2

    private val intervalTime = 5000.toLong()
    private var homeBannerHandler = BannerHandler()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        settingListener()
        setVp()
        setTeacherRcv()
        setRcv(3)
        binding.item4.setBackgroundResource(R.color.purple_200)

        binding.VPBanner.setCurrentItem(bannerPosition, false) //시작 위치 지정


        binding.VPBanner.apply {
            registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
                //이 메서드의 state 값으로 뷰페이저의 상태를 알 수 있음
                override fun onPageScrollStateChanged(state: Int) {
                    super.onPageScrollStateChanged(state)
                    when (state) {
                        //뷰페이저가 움직이는 중일 때 자동 스크롤 멈춤 함수 호출
                        ViewPager2.SCROLL_STATE_DRAGGING -> {
                            autoScrollStop()
                        }
                        //뷰페이저에서 손 뗐을 때, 뷰페이저가 멈춰있을 때 자동 스크롤 시작 함수 호출
                        ViewPager2.SCROLL_STATE_IDLE -> {
                            autoScrollStart(intervalTime)
                        }
                    }
                }

                override fun onPageScrolled(
                    position: Int,
                    positionOffset: Float,
                    positionOffsetPixels: Int,
                ) {
                    super.onPageScrolled(position, positionOffset, positionOffsetPixels)
                    bannerPosition = position
                }
            })
        }

    }

    override fun onClick(p0: View?) {
        when(p0){
            binding.MenuBtn -> {
                ClickMenu()
            }
            binding.item1 -> {
                setRcv(0)
                binding.item1.setBackgroundResource(R.color.purple_200)
                binding.item2.setBackgroundResource(R.color.white)
                binding.item3.setBackgroundResource(R.color.white)
                binding.item4.setBackgroundResource(R.color.white)
            }
            binding.item2 -> {
                setRcv(1)
                binding.item1.setBackgroundResource(R.color.white)
                binding.item2.setBackgroundResource(R.color.purple_200)
                binding.item3.setBackgroundResource(R.color.white)
                binding.item4.setBackgroundResource(R.color.white)
            }
            binding.item3 -> {
                setRcv(2)
                binding.item1.setBackgroundResource(R.color.white)
                binding.item2.setBackgroundResource(R.color.white)
                binding.item3.setBackgroundResource(R.color.purple_200)
                binding.item4.setBackgroundResource(R.color.white)
            }
            binding.item4 -> {
                setRcv(3)
                binding.item1.setBackgroundResource(R.color.white)
                binding.item2.setBackgroundResource(R.color.white)
                binding.item3.setBackgroundResource(R.color.white)
                binding.item4.setBackgroundResource(R.color.purple_200)
            }
        }

    }

    private fun onClickBtn() {

    }

    private fun setRcv(num : Int) {
        val LectureAdapter = Rcv_Adapter(this)
        binding.LectureGood.adapter = LectureAdapter
        binding.LectureGood.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        when(num){
            0 ->{
                LectureAdapter.data = mutableListOf(
                    Rcv_Item("https://post-phinf.pstatic.net/MjAyMDEyMTBfMTgy/MDAxNjA3NTYyNjA3MjYx.WOG_ubqXsoLbXctoQHwzH7Uvn0OBCzGDRaUw_tNVMSgg.DGF6K0cqTWKuobX312tKuAJKLoa52RYq1MkmZEpQ08gg.JPEG/6_1.jpg?type=w1200&type=w1200", "수학", "대충 수학 가르칩니다."),
                    Rcv_Item("http://file3.instiz.net/data/file3/2020/02/15/4/c/6/4c6f0b9f920906d42a7dbd58d5105a35.jpg", "국어", "대충 국어 가르칩니다."),
                    Rcv_Item("https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcT7FGfsHcAQ3JbDCTVIJKlPId8LBrIIfBkVfg&usqp=CAU", "역사", "대충 역사 가르칩니다."),
                    Rcv_Item("https://i.ytimg.com/vi/kAMukh7CMHw/maxresdefault.jpg", "김동영", "대충 영어 가르칩니다."),
                    )
            }
            1 -> {
                LectureAdapter.data = mutableListOf(
                    Rcv_Item("https://t1.daumcdn.net/cfile/blog/990565365E634A0737", "박성완", "대충 궁술 가르칩니다."),
                    Rcv_Item("https://t1.daumcdn.net/cfile/tistory/2455464557FB930D31", "김경준", "대충 창술 가르칩니다."),
                    Rcv_Item("https://cdn.k-trendynews.com/news/photo/202106/95171_125126_1821.jpg", "전원재", "대충 검술 가르칩니다."),
                    )
            }
            2 -> {
                LectureAdapter.data = mutableListOf(
                    Rcv_Item("http://image.dongascience.com/Photo/2019/12/efe86c4ca4fd155c8954b9be217c6eb9.jpg", "천부경", "대충 의술 가르칩니다."),
                    Rcv_Item("https://report.dbpia.co.kr/wp-content/uploads/2017/05/legal2_1.jpg", "김동진", "대충 명법업 가르칩니다."),
                    Rcv_Item("data:image/jpeg;base64,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", "최형석", "대충 침술 가르칩니다."),
                    )
            }
            3 -> {
                LectureAdapter.data = mutableListOf(
                    Rcv_Item("https://post-phinf.pstatic.net/MjAyMDEyMTBfMTgy/MDAxNjA3NTYyNjA3MjYx.WOG_ubqXsoLbXctoQHwzH7Uvn0OBCzGDRaUw_tNVMSgg.DGF6K0cqTWKuobX312tKuAJKLoa52RYq1MkmZEpQ08gg.JPEG/6_1.jpg?type=w1200&type=w1200", "수학", "대충 수학 가르칩니다."),
                    Rcv_Item("http://file3.instiz.net/data/file3/2020/02/15/4/c/6/4c6f0b9f920906d42a7dbd58d5105a35.jpg", "국어", "대충 국어 가르칩니다."),
                    Rcv_Item("https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcT7FGfsHcAQ3JbDCTVIJKlPId8LBrIIfBkVfg&usqp=CAU", "역사", "대충 역사 가르칩니다."),
                    Rcv_Item("https://i.ytimg.com/vi/kAMukh7CMHw/maxresdefault.jpg", "김동영", "대충 영어 가르칩니다."),
                    Rcv_Item("https://t1.daumcdn.net/cfile/blog/990565365E634A0737", "박성완", "대충 궁술 가르칩니다."),
                    Rcv_Item("https://t1.daumcdn.net/cfile/tistory/2455464557FB930D31", "김경준", "대충 창술 가르칩니다."),
                    Rcv_Item("https://cdn.k-trendynews.com/news/photo/202106/95171_125126_1821.jpg", "전원재", "대충 검술 가르칩니다."),
                    Rcv_Item("http://image.dongascience.com/Photo/2019/12/efe86c4ca4fd155c8954b9be217c6eb9.jpg", "천부경", "대충 의술 가르칩니다."),
                    Rcv_Item("https://report.dbpia.co.kr/wp-content/uploads/2017/05/legal2_1.jpg", "김동진", "대충 명법업 가르칩니다."),
                    Rcv_Item("data:image/jpeg;base64,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", "최형석", "대충 침술 가르칩니다."),
                    )
            }
        }
        LectureAdapter.notifyDataSetChanged()
    }


    //배너 자동 스크롤 시작하게 하는 함수
    private fun autoScrollStart(intervalTime: Long) {
        homeBannerHandler.removeMessages(0) //이거 안하면 핸들러가 여러개로 계속 늘어남
        homeBannerHandler.sendEmptyMessageDelayed(0, intervalTime) //intervalTime만큼 반복해서 핸들러를 실행
    }

    //배너 자동 스크롤 멈추게 하는 함수
    private fun autoScrollStop() {
        homeBannerHandler.removeMessages(0) //핸들러 중지
    }

    //배너 자동 스크롤 컨트롤하는 클래스
    private inner class BannerHandler : Handler(Looper.getMainLooper()) {
        override fun handleMessage(msg: Message) {
            super.handleMessage(msg)
            if (msg.what == 0) {
                binding.VPBanner.setCurrentItem(++bannerPosition, true) //다음 페이지로 이동
                autoScrollStart(intervalTime) //스크롤 킵고잉
            }
        }
    }

    //다른 화면으로 갔다가 돌아오면 배너 스크롤 다시 시작
    override fun onResume() {
        super.onResume()
        autoScrollStart(intervalTime)
    }

    //다른 화면을 보고 있는 동안에는 배너 스크롤 안함
    override fun onPause() {
        super.onPause()
        autoScrollStop()
    }

    private fun settingListener(){
        binding.MenuBtn.setOnClickListener(this)
        binding.item1.setOnClickListener(this)
        binding.item2.setOnClickListener(this)
        binding.item3.setOnClickListener(this)
        binding.item4.setOnClickListener(this)
    }

    private fun ClickMenu(){
        if(binding.MenuLayout.visibility == View.VISIBLE){
            binding.MenuLayout.visibility = View.GONE
        } else {
            binding.MenuLayout.visibility = View.VISIBLE
        }
    }

    private fun setVp() {
        val viewPagerAdapter = ViewPagerAdapter(this)
        binding.VPBanner.adapter = viewPagerAdapter // 어댑터 생성
        binding.VPBanner.orientation = ViewPager2.ORIENTATION_HORIZONTAL // 방향을 가로로
        viewPagerAdapter.data = listOf(
            BannerData("https://i.ibb.co/RHrZ2fx/Kakao-Talk-20220623-134127789.png"),
            BannerData("https://i.ibb.co/RHrZ2fx/Kakao-Talk-20220623-134127789.png"),
            BannerData("https://i.ibb.co/RHrZ2fx/Kakao-Talk-20220623-134127789.png")
        )
        viewPagerAdapter.notifyDataSetChanged()
    }

    private fun setTeacherRcv() {
        val TeacherAdapter = Rcv_Adapter(this)
        binding.TeacherGood.adapter = TeacherAdapter
        binding.TeacherGood.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        TeacherAdapter.data = mutableListOf(
            Rcv_Item("https://doryoung.com/img/sub/info_dory03_img01.jpg", "정병호", "현) 사단법인 한국전통서당문화진흥회 충청지부장\n현) 공주도령서당 훈장"),
            Rcv_Item("https://doryoung.com/img/sub/info_dory03_img02.jpg", "정민호", "현) 사단법인 한국전통서당문화진흥회 이사\n현) 공주도령서당 훈장"),
            Rcv_Item("https://doryoung.com/img/sub/info_dory03_img03.jpg", "이정옥", "현) 공주도령서당 사무관련담당 훈감님"),
            Rcv_Item("https://image.newsis.com/2013/08/28/NISI20130828_0008579353_web.jpg", "김봉곤", "현) 선촌서당 훈장\n한학 교육가 겸 시인")
        )
        TeacherAdapter.notifyDataSetChanged()
    }

}