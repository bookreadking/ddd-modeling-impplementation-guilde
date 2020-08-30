# チケットモデリング

お題の映画チケットの料金表
https://cinemacity.co.jp/ticket/

## ユースケース
料金表を読み解いてユースケースを書いてみた。

![](http://www.plantuml.com/plantuml/png/RP7DJi904CVlVOeD9ptm0ZqO7eHBWrL9i2HjkJkx0L4WA0Ib6hLHH8I8YWVvQF1XXkt7MpXG4Z5wdVdjpEy_cx9ipRGBXpb5qJ9stcGg2a3-ZaAWA0Vzfgm_g4opcANdShgf67bRPsPs_y1c-RrrKMK9eYBt1U4fk9-6_Hh29SATB1NbS8AyWVmJ-GH52OMBC0YSMLGThPXksjZSHspWJ6LvZE0WhoJ-bGe_F5by38-AAw-2zSUAIkGaJqRIQz54MQT5vyWhf8GSmkb0bajXpqFe8pmJ4ASaiR1Bp3MHQKEULH7QoB-GTvPYOe1YjXYV-ctNTtXqqKGO_ZA7rx1pxBVU_DihX3b2ZrPHlbYM3eDGGrvH59BBQ9REjZPVnw6tbtcobbNG5IM-B4icT-8AKzdcPMZCkXk3FstUMCnl8jTRSodTsANV_GO0)

- 前提
  - 身分証を見せるとの記述があるので窓口でのみ発券すると想定。
  - 複数の映画のチケットの同時購入は不可
  - 複数条件を満たす場合は1番安い料金で発券する（例えばシネマシチズンの平日料金と映画の日の料金のように）

- 出てくるワード
  - チケット
  - チケット料金
  - シネマシチズン
  - エムアイカード
  - 駐車場パーク80割引
  - 平日・休日
  - レイトショー
  - 映画の日
  - 3D作品
  - 3Dメガネ

## クラス図
![](http://www.plantuml.com/plantuml/png/bLJDZX914BxtKqmuhMPtezdhPjq35ozxy0qcivDruyYQ1KyR4wVRu0WXG40GDK6Y1eC4CM3uSQ8FK_GqS-8Lh7HZau5EZ2UgkkhxwgkFofncS_PrxkbZZpoyF7Sjv_BQyLn27C_EPgr5-9RNEjODiTQXUDtZvGccqR3CAoKCHBtDP-FeVH0rFvDdVu7Hcm-Y_bC2LNW_ZI7Gtq0_0Gk0VSNqABq1BAO5R9G05Kf00Zi3-WrO3rW8R7HsfJlZHf5tYr6BnesQVXG_w6AUtpo6LTIt2jjxjp03rb_he1DWNMIK5JqG_87krWg1-K2_0sCIfnPfIi762heZkaEiyja8oQ96AtvPtKfNOM2IyaeBrYXC_urAojTO5Io_16BGt5FZmI_YTli68x1oRp_10_WYRNXYEu_i2pUnhcZDUJ2Dt-N5-8LrVFp0TN9svi9pJqwi6vDnEtwTNMc5Pgj5VRJy5KgS2kFd7_4AjsWKm-wP7gM1TO3sWPN0fq0BXY5w3JL4XYWhSOYy4g21E2Bkb7IJl0xa8mQgrDsKTVlW07yF3pCfmqPazqc292O9CFBVGLxThPGIzRSf6ZrQrOpZaXpIkV-GfK-TdBgPS_o2_040)

- 極上爆音上映、特別興行は対象外とした
- 祭日は考慮していない
