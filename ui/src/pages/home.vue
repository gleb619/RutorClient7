<template>
  <f7-page>
    <f7-navbar>
      <f7-nav-left>
        <f7-link icon-if-ios="f7:menu" icon-if-md="material:menu" panel-open="left"></f7-link>
      </f7-nav-left>
      <f7-nav-title>My App</f7-nav-title>
      <f7-nav-right>
        <f7-link icon-if-ios="f7:menu" icon-if-md="material:menu" panel-open="right"></f7-link>
      </f7-nav-right>
    </f7-navbar>
    <f7-toolbar>
      <f7-link>Left Link</f7-link>
      <f7-link>Right Link</f7-link>
    </f7-toolbar>
    <f7-block strong>
      <p>Here is your blank Framework7 app. Let's see what we have here.</p>
    </f7-block>
    <f7-block-title>Navigation</f7-block-title>
    <f7-list>
      <f7-list-item link="/about/" title="About"></f7-list-item>
      <f7-list-item link="/form/" title="Form"></f7-list-item>
    </f7-list>
    <f7-block-title>Modals</f7-block-title>
    <f7-block strong>
      <f7-row>
        <f7-col width="50">
          <f7-button fill raised popup-open="#popup">Popup</f7-button>
        </f7-col>
        <f7-col width="50">
          <f7-button fill raised login-screen-open="#login-screen">Login Screen</f7-button>
        </f7-col>
      </f7-row>
    </f7-block>
    <f7-block-title>Panels</f7-block-title>
    <f7-block strong>
      <f7-row>
        <f7-col width="50">
          <f7-button fill raised panel-open="left">Left Panel</f7-button>
        </f7-col>
        <f7-col width="50">
          <f7-button fill raised panel-open="right">Right Panel</f7-button>
        </f7-col>
      </f7-row>
    </f7-block>
    <f7-list>
      <f7-list-item link="/dynamic-route/blog/45/post/125/?foo=bar#about" title="Dynamic Route"></f7-list-item>
      <f7-list-item link="/load-something-that-doesnt-exist/" title="Default Route (404)"></f7-list-item>
    </f7-list>

    <div v-html="desc">

    </div>

  </f7-page>
</template>
<script>
export default {
  data() {
    return {
      desc: '' +
      '<td><br><img src="http://img12.lostpic.net/2018/05/01/7c5976316085f6682afb32a6e8b23d3c.jpg"><br> <br> <u>Информация о фильме</u><br> <b>Название: </b>Гладиатор<br> <b>Оригинальное название: </b>Gladiator<br> <b>Год выхода: </b>2000<br> <b>Жанр: </b> <a href="/tag/1/Боевик" target="_blank">Боевик</a>, <a href="/tag/1/история" target="_blank">история</a>, <a href="/tag/1/драма" target="_blank">драма</a>, <a href="/tag/1/приключения" target="_blank">приключения</a><br> <b>Режиссер: </b>Ридли Скотт<br> <b>В ролях: </b>Расселл Кроу, Хоакин Феникс, Конни Нильсен, Оливер Рид, Ричард Харрис, Дерек Джейкоби, Джимон Хонсу, Дэвид Скофилд, Джон Шрэпнел, Томас Арана, Ральф Мёллер, Спенсер Трит Кларк, Дэвид Хеммингс, Томми Флэнаган, Свен-Оле Торсен<br> <br> <b>О фильме:</b><br> В великой Римской империи не было военачальника, равного генералу Максимусу. Непобедимые легионы, которыми командовал этот благородный воин, боготворили его и могли последовать за ним даже в ад. Но случилось так, что отважный Максимус, готовый сразиться с любым противником в честном бою, оказался бессилен против вероломных придворных интриг. Генерала предали и приговорили к смерти. Чудом избежав гибели, Максимус становится гладиатором. Быстро снискав себе славу в кровавых поединках, он оказывается в знаменитом римском Колизее, на арене которого он встретится в смертельной схватке со своим заклятым врагом…<br> <br> <br> <a href="http://www.imdb.com/title/tt0172495/" target="_blank"><img src="http://s.rutor.info/imdb/pic/0172495.gif"></a> <a href="http://www.kinopoisk.ru/level/1/film/474/" target="_blank"><img src="http://www.kinopoisk.ru/rating/474.gif"></a><br> <br> <br> <b>Страна: </b>США, Великобритания<br> <b>Студия: </b>DreamWorks SKG, Red Wagon Productions, Scott Free Productions, Universal Pictures<br> <b>Продолжительность: </b>02:50:58<br> <b>Перевод: </b>Дублированный, профессиональный многоголосый, авторский<br> <br> <u>Файл</u><br> <b>Кодек: </b>H.265<br> <b>Качество: </b>BDRip-HEVC 2160p<br> <b>Видео: </b>HEVC HDR, 21.0 Mбит/с, 3840x1600, 23.976 кадр/с<br> <b>Звук: </b><br> Русский (DTS, 6 ch, 768 Кбит/с) / Дублированный<br> Русский (DTS-HD MA, 6 ch, 4394 Кбит/с) / Профессиональный многоголосый (Телеканал «Интер»)<br> Русский (DTS-HD MA, 6 ch, 4385 Кбит/с) / Авторский (А.Гаврилов)<br> Русский (AC3, 6 ch, 448 Кбит/с) / Авторский (Визгунов)<br> Английский (DTS-X MA, 8 ch, 3840 Кбит/с)<br> <b>Субтитры: </b>Русские, английские (Full, SDH)<br> <br> <b><a href="http://sendfile.su/1416561" target="_blank">Семпл</a></b><br> <br> \n' +
      ' <div class="hidewrap">\n' +
      '  <div class="hidehead plus" onclick="hideshow($(this))">\n' +
      '   MI\n' +
      '  </div>\n' +
      '  <div class="hidebody"></div>\n' +
      '  <textarea class="hidearea">General&lt;br /&gt;\n' +
      'Unique ID                                : 288545278763893424446432240547456246998 (0xD913CC9CD8D17DAEA804EA50CEDE64D6)&lt;br /&gt;\n' +
      'Complete name                            : D:uTorrentGladiator.2000.EXTENDED.BDRip.2160p.HDR.MediaClub.mkv&lt;br /&gt;\n' +
      'Format                                   : Matroska&lt;br /&gt;\n' +
      'Format version                           : Version 4 / Version 2&lt;br /&gt;\n' +
      'File size                                : 41.5 GiB&lt;br /&gt;\n' +
      'Duration                                 : 2 h 50 min&lt;br /&gt;\n' +
      'Overall bit rate                         : 34.8 Mb/s&lt;br /&gt;\n' +
      'Encoded date                             : UTC 2018-04-29 18:52:36&lt;br /&gt;\n' +
      'Writing application                      : mkvmerge v19.0.0 (\'Brave Captain\') 64-bit&lt;br /&gt;\n' +
      'Writing library                          : libebml v1.3.5 + libmatroska v1.4.8&lt;br /&gt;\n' +
      'Cover                                    : Yes&lt;br /&gt;\n' +
      'Attachments                              : cover.jpg&lt;br /&gt;\n' +
      '&lt;br /&gt;\n' +
      'Video&lt;br /&gt;\n' +
      'ID                                       : 1&lt;br /&gt;\n' +
      'Format                                   : HEVC&lt;br /&gt;\n' +
      'Format/Info                              : High Efficiency Video Coding&lt;br /&gt;\n' +
      'Commercial name                          : HDR10&lt;br /&gt;\n' +
      'Format profile                           : Main 10@L5.1@High&lt;br /&gt;\n' +
      'Codec ID                                 : V_MPEGH/ISO/HEVC&lt;br /&gt;\n' +
      'Duration                                 : 2 h 50 min&lt;br /&gt;\n' +
      'Bit rate                                 : 21.0 Mb/s&lt;br /&gt;\n' +
      'Width                                    : 3 840 pixels&lt;br /&gt;\n' +
      'Height                                   : 1 600 pixels&lt;br /&gt;\n' +
      'Display aspect ratio                     : 2.40:1&lt;br /&gt;\n' +
      'Frame rate mode                          : Constant&lt;br /&gt;\n' +
      'Frame rate                               : 23.976 (24000/1001) FPS&lt;br /&gt;\n' +
      'Color space                              : YUV&lt;br /&gt;\n' +
      'Chroma subsampling                       : 4:2:0 (Type 2)&lt;br /&gt;\n' +
      'Bit depth                                : 10 bits&lt;br /&gt;\n' +
      'Bits/(Pixel*Frame)                       : 0.142&lt;br /&gt;\n' +
      'Stream size                              : 25.0 GiB (60%)&lt;br /&gt;\n' +
      'Title                                    : BDRip.2160p.HDR by Silverok&lt;br /&gt;\n' +
      'Writing library                          : x265 2.7+340-aa9102400f24:[Windows][GCC 7.3.0][64 bit] 10bit&lt;br /&gt;\n' +
      'Encoding settings                        : cpuid=1176575 / frame-threads=5 / numa-pools=32 / wpp / no-pmode / no-pme / no-psnr / no-ssim / log-level=2 / input-csp=1 / input-res=3840x1600 / interlace=0 / total-frames=245910 / level-idc=51 / high-tier=1 / uhd-bd=0 / ref=4 / no-allow-non-conformance / repeat-headers / annexb / aud / hrd / info / hash=0 / no-temporal-layers / no-open-gop / min-keyint=24 / keyint=240 / gop-lookahead=0 / bframes=4 / b-adapt=2 / b-pyramid / bframe-bias=0 / rc-lookahead=25 / lookahead-slices=4 / scenecut=40 / radl=0 / no-intra-refresh / ctu=64 / min-cu-size=8 / rect / no-amp / max-tu-size=32 / tu-inter-depth=1 / tu-intra-depth=1 / limit-tu=0 / rdoq-level=2 / dynamic-rd=0.00 / no-ssim-rd / signhide / no-tskip / nr-intra=0 / nr-inter=0 / no-constrained-intra / no-strong-intra-smoothing / max-merge=3 / limit-refs=3 / limit-modes / me=3 / subme=3 / merange=57 / temporal-mvp / weightp / no-weightb / no-analyze-src-pics / deblock=-3:-3 / no-sao / no-sao-non-deblock / rd=4 / no-early-skip / rskip / no-fast-intra / no-tskip-fast / no-cu-lossless / no-b-intra / no-splitrd-skip / rdpenalty=0 / psy-rd=2.00 / psy-rdoq=1.00 / no-rd-refine / no-lossless / cbqpoffs=0 / crqpoffs=0 / rc=crf / crf=17.0 / qcomp=0.60 / qpstep=4 / stats-write=0 / stats-read=0 / vbv-maxrate=160000 / vbv-bufsize=160000 / vbv-init=0.9 / crf-max=0.0 / crf-min=0.0 / ipratio=1.40 / pbratio=1.30 / aq-mode=1 / aq-strength=1.00 / cutree / zone-count=0 / no-strict-cbr / qg-size=32 / no-rc-grain / qpmax=69 / qpmin=0 / no-const-vbv / sar=1 / overscan=0 / videoformat=5 / range=0 / colorprim=9 / transfer=16 / colormatrix=9 / chromaloc=1 / chromaloc-top=2 / chromaloc-bottom=2 / display-window=0 / master-display=G(13250,34500)B(7500,3000)R(34000,16000)WP(15635,16450)L(10000000,50) / max-cll=1000,497 / min-luma=0 / max-luma=1023 / log2-max-poc-lsb=8 / vui-timing-info / vui-hrd-info / slices=1 / no-opt-qp-pps / no-opt-ref-list-length-pps / no-multi-pass-opt-rps / scenecut-bias=0.05 / no-opt-cu-delta-qp / no-aq-motion / hdr / hdr-opt / no-dhdr10-opt / no-idr-recovery-sei / analysis-reuse-level=5 / scale-factor=0 / refine-intra=0 / refine-inter=0 / refine-mv=0 / no-limit-sao / ctu-info=0 / no-lowpass-dct / refine-mv-type=0 / copy-pic=1 / max-ausize-factor=1.0 / no-dynamic-refine / no-single-sei&lt;br /&gt;\n' +
      'Default                                  : Yes&lt;br /&gt;\n' +
      'Forced                                   : No&lt;br /&gt;\n' +
      'Color range                              : Limited&lt;br /&gt;\n' +
      'Color primaries                          : BT.2020&lt;br /&gt;\n' +
      'Transfer characteristics                 : PQ&lt;br /&gt;\n' +
      'Matrix coefficients                      : BT.2020 non-constant&lt;br /&gt;\n' +
      'Mastering display color primaries        : BT.709&lt;br /&gt;\n' +
      'Mastering display luminance              : min: 0.0050 cd/m2, max: 1000 cd/m2&lt;br /&gt;\n' +
      'Maximum Content Light Level              : 1000 cd/m2&lt;br /&gt;\n' +
      'Maximum Frame-Average Light Level        : 497 cd/m2&lt;br /&gt;\n' +
      '&lt;br /&gt;\n' +
      'Audio #1&lt;br /&gt;\n' +
      'ID                                       : 2&lt;br /&gt;\n' +
      'Format                                   : DTS&lt;br /&gt;\n' +
      'Format/Info                              : Digital Theater Systems&lt;br /&gt;\n' +
      'Codec ID                                 : A_DTS&lt;br /&gt;\n' +
      'Duration                                 : 2 h 47 min&lt;br /&gt;\n' +
      'Bit rate mode                            : Constant&lt;br /&gt;\n' +
      'Bit rate                                 : 768 kb/s&lt;br /&gt;\n' +
      'Channel(s)                               : 6 channels&lt;br /&gt;\n' +
      'Channel positions                        : Front: L C R, Side: L R, LFE&lt;br /&gt;\n' +
      'Sampling rate                            : 48.0 kHz&lt;br /&gt;\n' +
      'Frame rate                               : 93.750 FPS (512 SPF)&lt;br /&gt;\n' +
      'Bit depth                                : 24 bits&lt;br /&gt;\n' +
      'Compression mode                         : Lossy&lt;br /&gt;\n' +
      'Stream size                              : 923 MiB (2%)&lt;br /&gt;\n' +
      'Title                                    : Дубляж&lt;br /&gt;\n' +
      'Language                                 : Russian&lt;br /&gt;\n' +
      'Default                                  : Yes&lt;br /&gt;\n' +
      'Forced                                   : No&lt;br /&gt;\n' +
      '&lt;br /&gt;\n' +
      'Audio #2&lt;br /&gt;\n' +
      'ID                                       : 3&lt;br /&gt;\n' +
      'Format                                   : DTS&lt;br /&gt;\n' +
      'Format/Info                              : Digital Theater Systems&lt;br /&gt;\n' +
      'Format profile                           : MA / Core&lt;br /&gt;\n' +
      'Codec ID                                 : A_DTS&lt;br /&gt;\n' +
      'Duration                                 : 2 h 50 min&lt;br /&gt;\n' +
      'Bit rate mode                            : Variable / Constant&lt;br /&gt;\n' +
      'Bit rate                                 : 4 394 kb/s / 1 509 kb/s&lt;br /&gt;\n' +
      'Channel(s)                               : 6 channels&lt;br /&gt;\n' +
      'Channel positions                        : Front: L C R, Side: L R, LFE&lt;br /&gt;\n' +
      'Sampling rate                            : 48.0 kHz&lt;br /&gt;\n' +
      'Frame rate                               : 93.750 FPS (512 SPF)&lt;br /&gt;\n' +
      'Bit depth                                : 24 bits&lt;br /&gt;\n' +
      'Compression mode                         : Lossless / Lossy&lt;br /&gt;\n' +
      'Stream size                              : 5.25 GiB (13%)&lt;br /&gt;\n' +
      'Title                                    : профессиональный многоголосый (Телеканал Интер)&lt;br /&gt;\n' +
      'Language                                 : Russian&lt;br /&gt;\n' +
      'Default                                  : No&lt;br /&gt;\n' +
      'Forced                                   : No&lt;br /&gt;\n' +
      '&lt;br /&gt;\n' +
      'Audio #3&lt;br /&gt;\n' +
      'ID                                       : 4&lt;br /&gt;\n' +
      'Format                                   : DTS&lt;br /&gt;\n' +
      'Format/Info                              : Digital Theater Systems&lt;br /&gt;\n' +
      'Format profile                           : MA / Core&lt;br /&gt;\n' +
      'Codec ID                                 : A_DTS&lt;br /&gt;\n' +
      'Duration                                 : 2 h 50 min&lt;br /&gt;\n' +
      'Bit rate mode                            : Variable / Constant&lt;br /&gt;\n' +
      'Bit rate                                 : 4 385 kb/s / 1 509 kb/s&lt;br /&gt;\n' +
      'Channel(s)                               : 6 channels&lt;br /&gt;\n' +
      'Channel positions                        : Front: L C R, Side: L R, LFE&lt;br /&gt;\n' +
      'Sampling rate                            : 48.0 kHz&lt;br /&gt;\n' +
      'Frame rate                               : 93.750 FPS (512 SPF)&lt;br /&gt;\n' +
      'Bit depth                                : 24 bits&lt;br /&gt;\n' +
      'Compression mode                         : Lossless / Lossy&lt;br /&gt;\n' +
      'Stream size                              : 5.24 GiB (13%)&lt;br /&gt;\n' +
      'Title                                    : авторский (А.Гаврилов)&lt;br /&gt;\n' +
      'Language                                 : Russian&lt;br /&gt;\n' +
      'Default                                  : No&lt;br /&gt;\n' +
      'Forced                                   : No&lt;br /&gt;\n' +
      '&lt;br /&gt;\n' +
      'Audio #4&lt;br /&gt;\n' +
      'ID                                       : 5&lt;br /&gt;\n' +
      'Format                                   : AC-3&lt;br /&gt;\n' +
      'Format/Info                              : Audio Coding 3&lt;br /&gt;\n' +
      'Codec ID                                 : A_AC3&lt;br /&gt;\n' +
      'Duration                                 : 2 h 50 min&lt;br /&gt;\n' +
      'Bit rate mode                            : Constant&lt;br /&gt;\n' +
      'Bit rate                                 : 448 kb/s&lt;br /&gt;\n' +
      'Channel(s)                               : 6 channels&lt;br /&gt;\n' +
      'Channel positions                        : Front: L C R, Side: L R, LFE&lt;br /&gt;\n' +
      'Sampling rate                            : 48.0 kHz&lt;br /&gt;\n' +
      'Frame rate                               : 31.250 FPS (1536 SPF)&lt;br /&gt;\n' +
      'Bit depth                                : 16 bits&lt;br /&gt;\n' +
      'Compression mode                         : Lossy&lt;br /&gt;\n' +
      'Stream size                              : 548 MiB (1%)&lt;br /&gt;\n' +
      'Title                                    : авторский (Визгунов)&lt;br /&gt;\n' +
      'Language                                 : Russian&lt;br /&gt;\n' +
      'Service kind                             : Complete Main&lt;br /&gt;\n' +
      'Default                                  : No&lt;br /&gt;\n' +
      'Forced                                   : No&lt;br /&gt;\n' +
      '&lt;br /&gt;\n' +
      'Audio #5&lt;br /&gt;\n' +
      'ID                                       : 6&lt;br /&gt;\n' +
      'Format                                   : DTS&lt;br /&gt;\n' +
      'Format/Info                              : Digital Theater Systems&lt;br /&gt;\n' +
      'Format profile                           : X / MA / Core&lt;br /&gt;\n' +
      'Codec ID                                 : A_DTS&lt;br /&gt;\n' +
      'Duration                                 : 2 h 50 min&lt;br /&gt;\n' +
      'Bit rate mode                            : Variable / Variable / Constant&lt;br /&gt;\n' +
      'Bit rate                                 : 3 840 kb/s / 3 840 kb/s / 1 509 kb/s&lt;br /&gt;\n' +
      'Channel(s)                               : Object Based / 8 channels / 6 channels&lt;br /&gt;\n' +
      'Channel positions                        : Object Based / Front: L C R, Side: L R, Back: L R, LFE / Front: L C R, Side: L R, LFE&lt;br /&gt;\n' +
      'Sampling rate                            :  / 48.0 kHz / 48.0 kHz&lt;br /&gt;\n' +
      'Frame rate                               : 93.750 FPS (512 SPF)&lt;br /&gt;\n' +
      'Bit depth                                :  / 24 bits / 24 bits&lt;br /&gt;\n' +
      'Compression mode                         :  / Lossless / Lossy&lt;br /&gt;\n' +
      'Stream size                              : 4.56 GiB (11%)&lt;br /&gt;\n' +
      'Language                                 : English&lt;br /&gt;\n' +
      'Default                                  : No&lt;br /&gt;\n' +
      'Forced                                   : No&lt;br /&gt;\n' +
      '&lt;br /&gt;\n' +
      'Text #1&lt;br /&gt;\n' +
      'ID                                       : 7&lt;br /&gt;\n' +
      'Format                                   : UTF-8&lt;br /&gt;\n' +
      'Codec ID                                 : S_TEXT/UTF8&lt;br /&gt;\n' +
      'Codec ID/Info                            : UTF-8 Plain Text&lt;br /&gt;\n' +
      'Duration                                 : 2 h 45 min&lt;br /&gt;\n' +
      'Bit rate                                 : 57 b/s&lt;br /&gt;\n' +
      'Count of elements                        : 1377&lt;br /&gt;\n' +
      'Stream size                              : 69.3 KiB (0%)&lt;br /&gt;\n' +
      'Title                                    : Full&lt;br /&gt;\n' +
      'Language                                 : Russian&lt;br /&gt;\n' +
      'Default                                  : Yes&lt;br /&gt;\n' +
      'Forced                                   : No&lt;br /&gt;\n' +
      '&lt;br /&gt;\n' +
      'Text #2&lt;br /&gt;\n' +
      'ID                                       : 8&lt;br /&gt;\n' +
      'Format                                   : UTF-8&lt;br /&gt;\n' +
      'Codec ID                                 : S_TEXT/UTF8&lt;br /&gt;\n' +
      'Codec ID/Info                            : UTF-8 Plain Text&lt;br /&gt;\n' +
      'Duration                                 : 2 h 40 min&lt;br /&gt;\n' +
      'Bit rate                                 : 35 b/s&lt;br /&gt;\n' +
      'Count of elements                        : 1364&lt;br /&gt;\n' +
      'Stream size                              : 41.5 KiB (0%)&lt;br /&gt;\n' +
      'Title                                    : Full&lt;br /&gt;\n' +
      'Language                                 : English&lt;br /&gt;\n' +
      'Default                                  : No&lt;br /&gt;\n' +
      'Forced                                   : No&lt;br /&gt;\n' +
      '&lt;br /&gt;\n' +
      'Text #3&lt;br /&gt;\n' +
      'ID                                       : 9&lt;br /&gt;\n' +
      'Format                                   : UTF-8&lt;br /&gt;\n' +
      'Codec ID                                 : S_TEXT/UTF8&lt;br /&gt;\n' +
      'Codec ID/Info                            : UTF-8 Plain Text&lt;br /&gt;\n' +
      'Duration                                 : 2 h 41 min&lt;br /&gt;\n' +
      'Bit rate                                 : 38 b/s&lt;br /&gt;\n' +
      'Count of elements                        : 1626&lt;br /&gt;\n' +
      'Stream size                              : 45.5 KiB (0%)&lt;br /&gt;\n' +
      'Title                                    : SDH&lt;br /&gt;\n' +
      'Language                                 : Russian&lt;br /&gt;\n' +
      'Default                                  : No&lt;br /&gt;\n' +
      'Forced                                   : No&lt;br /&gt;\n' +
      '&lt;br /&gt;\n' +
      'Menu&lt;br /&gt;\n' +
      '00:00:00.000                             : :Far From Home (Main Title)&lt;br /&gt;\n' +
      '00:07:14.308                             : :Hell Unleashed&lt;br /&gt;\n' +
      '00:12:51.103                             : :You Have Missed the War&lt;br /&gt;\n' +
      '00:18:45.791                             : :A Pleasant Fiction&lt;br /&gt;\n' +
      '00:23:30.325                             : :One More Duty&lt;br /&gt;\n' +
      '00:32:59.394                             : :You Will Not Be Emperor&lt;br /&gt;\n' +
      '00:37:35.878                             : :Hail Caesar&lt;br /&gt;\n' +
      '00:40:51.740                             : :A Soldier\'s Death&lt;br /&gt;\n' +
      '00:47:33.725                             : :Mark of the Legion&lt;br /&gt;\n' +
      '00:51:36.843                             : :Gladiators, I Salute You&lt;br /&gt;\n' +
      '00:55:45.383                             : :Battle in Chains&lt;br /&gt;\n' +
      '01:02:22.280                             : :The Greatness of Rome&lt;br /&gt;\n' +
      '01:09:57.985                             : :Win the Crowd&lt;br /&gt;\n' +
      '01:19:09.286                             : :I Shall Cheer for You&lt;br /&gt;\n' +
      '01:29:22.648                             : :The Battle of Carthage&lt;br /&gt;\n' +
      '01:37:12.785                             : :My Name Is Gladiator&lt;br /&gt;\n' +
      '01:41:36.798                             : :Terribly Vexed&lt;br /&gt;\n' +
      '01:51:59.837                             : :A Man for the People&lt;br /&gt;\n' +
      '01:56:37.281                             : :Maximus the Merciful&lt;br /&gt;\n' +
      '02:00:44.487                             : :You Simply Won\'t Die&lt;br /&gt;\n' +
      '02:04:43.225                             : :The Last Wish of a Dying Man&lt;br /&gt;\n' +
      '02:12:20.015                             : :I\'m an Entertainer&lt;br /&gt;\n' +
      '02:13:33.588                             : :Busy Little Bee&lt;br /&gt;\n' +
      '02:24:16.439                             : :Shadows and Dust&lt;br /&gt;\n' +
      '02:28:49.670                             : :Am I Not Merciful&lt;br /&gt;\n' +
      '02:34:21.752                             : :Death Smiles at Us All&lt;br /&gt;\n' +
      '02:38:44.556                             : :Home Again&lt;br /&gt;\n' +
      '02:42:58.727                             : :Now We Are Free</textarea>\n' +
      ' </div><br> \n' +
      ' <div class="hidewrap">\n' +
      '  <div class="hidehead plus" onclick="hideshow($(this))">\n' +
      '   Скриншоты\n' +
      '  </div>\n' +
      '  <div class="hidebody"></div>\n' +
      '  <textarea class="hidearea">&lt;a href="http://lostpic.net/image/c76N" target="_blank"&gt;&lt;img src="http://img12.lostpic.net/2018/05/01/91e739076f0c1baa7334e485a8833a0a.th.jpg" /&gt;&lt;/a&gt; &lt;a href="http://lostpic.net/image/c76T" target="_blank"&gt;&lt;img src="http://img12.lostpic.net/2018/05/01/3cfdf9be83eacfa46d1b062e0a29e6f9.th.jpg" /&gt;&lt;/a&gt; &lt;a href="http://lostpic.net/image/c76j" target="_blank"&gt;&lt;img src="http://img12.lostpic.net/2018/05/01/15b5e39c6f4182fcee2e86280e44084b.th.jpg" /&gt;&lt;/a&gt; &lt;a href="http://lostpic.net/image/c768" target="_blank"&gt;&lt;img src="http://img12.lostpic.net/2018/05/01/0ebf382ecf3336016d450efbf9ab61a9.th.jpg" /&gt;&lt;/a&gt; &lt;a href="http://lostpic.net/image/c76d" target="_blank"&gt;&lt;img src="http://img12.lostpic.net/2018/05/01/6e43851b505bde891249771ca7328a82.th.jpg" /&gt;&lt;/a&gt; &lt;a href="http://lostpic.net/image/c76G" target="_blank"&gt;&lt;img src="http://img12.lostpic.net/2018/05/01/34e4d2ebfc274b274cf2240c6b625257.th.jpg" /&gt;&lt;/a&gt; &lt;a href="http://lostpic.net/image/c76J" target="_blank"&gt;&lt;img src="http://img12.lostpic.net/2018/05/01/8b0d1d4b92c52ac00a99eaa838b9941a.th.jpg" /&gt;&lt;/a&gt; &lt;a href="http://lostpic.net/image/c76O" target="_blank"&gt;&lt;img src="http://img12.lostpic.net/2018/05/01/a2059ccccba7463178fd2753e0fce6a6.th.jpg" /&gt;&lt;/a&gt;</textarea>\n' +
      ' </div><br> <br> <b>Релиз от: </b><img src="http://s020.radikal.ru/i715/1302/33/01a1652712ec.gif"> | <b>Silverok</b></td>' +
      ''
    }
  }
}
</script>
