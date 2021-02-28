package com.example.androiddevchallenge.ui.puppieslist

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.androiddevchallenge.R
import com.example.androiddevchallenge.ui.puppieslist.other.PuppiesListUiData
import com.example.androiddevchallenge.ui.puppieslist.other.PuppiesListUiState
import com.example.androiddevchallenge.ui.puppieslist.other.PuppyData
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class PuppiesListViewModel : ViewModel() {
    private val puppiesListUiData = PuppiesListUiData(
        puppiesDate = mutableListOf(
            PuppyData(
                "ポメラニアン", R.drawable.dog_p, """
                ポメラニアンはドイツ原産の犬種。
                ポメラニアンという名称は、バルト海に面し、ドイツ北東部からポーランド北西部にまたがるポメラニア地方にちなんでいる。
                この地方では、古来より様々なスピッツが飼育されていた。
                ポメラニアンはドイツで中型のスピッツから品種改良を重ねることで小型化し、誕生した犬種である。
                そのため国際畜犬連盟からもジャーマン・スピッツの一品種に分類されており、多くの国で小さなスピッツを意味する「ツヴェルク・スピッツ」（Zwergspitz）として知られている[1]。
                
                ポメラニアンが流行犬種となったきっかけは、17世紀以降多くの王族が飼育を始めたことによる。
                とくに愛犬家として知られるヴィクトリア女王が小さな体躯のポメラニアンを愛好し、熱心に繁殖させたことによってポメラニアンの小型化に拍車がかかり、世界的な人気犬種となっていった（一説には1888年、女王自らがこの犬をイタリアから持ち帰ったともいうが、一般にはもう少し以前からイギリスで飼われていたと考えられる）。ヴィクトリア女王の存命中にポメラニアンの大きさはそれまでの半分程度にまで小さくなった。概してポメラニアンは頑健で丈夫な犬種といえるが、膝蓋骨脱臼と気管虚脱を発症することがある。また、まれにではあるが、「黒斑病 (black skin disease)」と呼ばれる遺伝性の皮膚疾患による脱毛症に罹患することもある[2]。アメリカでは近年飼育数がつねに上位15位までに入っており、世界的な小型犬の流行に一役買っている犬種となっている。
            """.trimIndent()
            ),
            PuppyData(
                "紫犬", R.drawable.dog_s, """
                柴犬（しばいぬ）は、日本原産の日本犬の一種。
                
                日本犬の中で唯一の小型犬で、オスは体高38 - 41cm、メスは35 - 38cmの犬種。
                日本の天然記念物に指定された7つの日本犬種（現存は6犬種）の1つで、指定は1936年（昭和11年）12月16日。
                日本における飼育頭数は最も多い。
                日本犬保存会によれば、現在[いつ?]日本で飼育されている日本犬種（6犬種）のうち、柴犬は約80%を占める。
                日本国外でも人気が高く、日本語の読みをそのままローマ字にした「Shiba Inu」という名前で呼ばれている。基本的には小型犬に分類される。
                
                特にヨーロッパでは柴犬の飼い主が集うコミュニティがある。
            """.trimIndent()
            ),
            PuppyData(
                "ブルドッグ", R.drawable.dog_b, """
                ブルドッグ（Bulldog）は、18世紀ごろの英国で雄牛（ブル）と犬を戦わせる牛いじめ（bullbaiting）という見世物が流行し、牛に対抗できる犬として開発された犬の品種の一つ[1]。
                1835年にイギリスで動物虐待法（Cruelty to Animals Act）が成立し、牛いじめを含めたブラッド・スポーツが禁止されると、ブルドッグは番犬や愛玩犬となった。
                
                闘争に必要だった獰猛な性格も取り去られ、現在では強面とは裏腹に、温厚且つおとなしい[1]。
                漫画に出てくる“棘状スタッドの付いた革首輪をはめ、誰にでも唸りかかったり噛み付いたりする”という描写はカリカチュアである。


                頭部
                ブルドッグの特徴のある顔つきと体型について、「しわしわの顔はけがをしにくいように皮膚が伸びたためであり、低い鼻は牛に噛みつきながら呼吸ができるためである」と説明されてきたが、今ではこれらは事実ではないことが分かっている。牛いじめに使われた当時の絵に出てくるブルドッグは、少し筋肉質で、体格も少し強そうな普通の犬でしかない。これらは現代のブルドッグと区別するためにオールド・イングリッシュ・ブルドッグと呼ばれている。今のような形になったのは、1800年代後半から大きく人間による選択が始まったからである。
            """.trimIndent()
            ),
            PuppyData(
                "ダックスフント", R.drawable.dog_d, """
                ダックスフント（独: Dachshund）は、ドイツ原産の犬種。
                ジャパンケネルクラブ（JKC）の登録名は英語読みによるダックスフンドだが、ドイツ語では文節末尾の d は濁らないため「フント」。

                ドイツ語のアナグマを表すダックス（Dachs）と、犬を表すフント（Hund）を合わせた「アナグマ犬」を意味する。
                巣穴の中にいるアナグマを狩る目的で手足が短く改良された。なお、ドイツ語フント（Hund）は 英語で猟犬を表すハウンド（hound）と同根。
                フランス語ではteckel。Teckelもドイツ語（北部ドイツ語）でダックスフントのこと。ドイツ語ではDackel（Dachshundの短縮形）とも言う。

                ジャパンケネルクラブ登録頭数は、記録の残る1999年から2007年までは1位、2008年以降は3位[1]。 また、アメリカでも人気があり、アメリカンケネルクラブでの登録頭数は常に10位以内に入る[2]。
            """.trimIndent()
            )
        )
    )
    private val _uiState: MutableStateFlow<PuppiesListUiState> =
        MutableStateFlow(PuppiesListUiState.Loading)
    val uiState: StateFlow<PuppiesListUiState> = _uiState

    init {
        viewModelScope.launch {
            _uiState.value = PuppiesListUiState.Success(puppiesListUiData)
        }
    }
}