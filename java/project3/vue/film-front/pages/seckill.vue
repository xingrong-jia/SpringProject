<template>
  <section class="seckill-div">
    <div class="seckill-panel">
      <!--选择list-->
      <SeckillItems :seckillData1="seckillData" :totalPage="totalPage"/>
    </div>
  </section>
</template>
<script>
    import SeckillItems from '~/components/seckill/SeckillItems.vue'
    import Cookies from 'js-cookie'
    import {getData} from '../plugins/axios'

    export default {
        head() {
            return {
                title: '秒杀',
                meta: [
                    {hid: '秒杀', name: '秒杀', content: '秒杀'}
                ]
            }
        },
        provide () {
            return {
                reload: this.reload
            }
        },
        components: {
            SeckillItems
        },
        data() {
            return {
                isRouterAlive: true,
                seckillTags: {},
                seckillData: {},
                totalPage: 0,
                nowPage: 1
            }
        },
        mounted() {
            this.getSeckillTags();
        },
        methods: {
            reload () {
                this.isRouterAlive = false
                this.$nextTick(function () {
                    this.isRouterAlive = true
                })
            },
            getSeckillTags() {
                let _this = this;
                let params = {
                    "brandId": this.$router.history.current.query.brandId || 99,//影院编号
                    "hallType": this.$router.history.current.query.halltypeId || 99,//影厅类型
                    "areaId": this.$router.history.current.query.areaId || 99,//行政区编号
                };
                getData(process.env.baseUrl + '/cinema/getCondition', 'get', params).then((res) => {
                    if (res && res.status == 0) {
                        _this.seckillTags = res.data;
                        let cinemaParams = Object.assign({}, params,
                            {
                                pageSize: 12,//每页条数
                                nowPage: _this.nowPage//当前页数
                            })
                        _this.getSeckillList(cinemaParams);
                    } else {
                        if (res.message) {
                            alert(res.message)
                        }
                    }
                }, (err) => {
                    console.log(err);
                })
            },
            getSeckillList(params) {
                getData(process.env.baseUrl + '/promo/getPromo', 'get', params).then((res) => {
                    if (res && res.status == 0) {
                        if (res.data && res.data.length > 0) {
                            this.seckillData = res.data;
                            for (const seckill of this.seckillData) {
                                seckill.number1=1
                            }
                            this.nowPage = res.nowPage;
                            this.totalPage = res.totalPage;
                            Cookies.set('cinemaNowPage', this.nowPage, { expires: 7, path: '' });
                        } else {
                            this.seckillData = [];
                            if (res.msg) {
                                alert(res.msg)
                            }
                        }
                    } else {
                        if (res.msg) {
                            alert(res.msg)
                        }
                    }
                }, (err) => {
                    console.log(err);
                });
            }
        }
    }
</script>
<style>
  .seckill-div{
    background-color: #fff;
    padding: 61px 0;
  }
  .seckill-panel{
    margin: 0 auto;
    width: 1200px;
  }
  .cinema-list{
    margin: 50px 0;
  }
</style>
