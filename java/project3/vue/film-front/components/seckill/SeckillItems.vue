<template>
    <div class="seckill-wrapper">
        <div class="seckill-list">
            <h2 class="seckill-list-header">秒杀列表</h2>

            <!--***********************-->

            <div class="has-movies" v-if="seckillData1.length > 0">
                <div v-for="(seckill,index) in seckillData1" :key="index" class="seckill-cell">

                    <!--****-->

                    <div class="div-img">
                        <!--<img class="seckill-img" src="../../static/cinema.jpg">-->
                        <img class="seckill-img" :src="imgUrl+seckill.imgAddress">
                    </div>
                    <div class="div-description">
                        <div class="left">
                            <div class="description">
                                <div class="description-title">详情:</div>
                                <div class="description-inner">{{seckill.description}}</div>
                            </div>
                            <div class="cinemaName">影院: {{seckill.cinemaName}}</div>
                            <div class="cinemaAddress">地址: {{seckill.cinemaAddress}}</div>
                            <div class="time">时间: {{seckill.startTime}}----{{seckill.endTime}}</div>
                        </div>

                        <div class="middle">
                            <div class="stock-title">当前库存</div>
                            <div class="stock"  style="font-family: 'Dancing Script', cursive;">{{seckill.stock}}</div>
                        </div>
                        <div class="right">
                            <div class="old-price">￥{{seckill.price*2}}</div>
                            <div class="price">
                                ￥{{seckill.price}}
                            </div>
                            <div class="number">
                                    <div><button class="plus" @click="plus(seckill.stock,index)">+</button></div>
                                    <input type="text" class="text" v-model="seckill.number1" />
                                <!--<div class="text">{{}}</div>-->
                                    <div><button @click="subtract(index)" class="subtract">-</button></div>
                            </div>
                            <!--<div class="status">{{seckill.status}}</div>-->
                            <button class="status" @click="order(seckill.uuid,index)">马上抢</button>
                        </div>

                    </div>
                    <!--****-->

                </div>
            </div>

            <!--**********************-->

            <div class="no-seckill" v-else>
                抱歉，没有找到相关结果，请尝试用其他条件筛选。
            </div>
        </div>
        <div class="seckill-pager" v-if="seckillData1 && seckillData1.length > 0">
            <ul class="list-pager">
                <li v-if="currentPage > 1"><a v-on:click="currentPage--, pageClick()">上一页</a></li>
                <li v-if="currentPage == 1"><a class="banclick">上一页</a></li>
                <li v-for="index in indexs" v-bind:class="{ 'active': currentPage == index }" :key="index">
                    <a v-on:click="btnClick(index)">{{ index }}</a>
                </li>
                <li v-if="currentPage != totalPage"><a v-on:click="currentPage++, pageClick()">下一页</a></li>
                <li v-if="currentPage == totalPage"><a class="banclick">下一页</a></li>
                <li><a>共<i>{{totalPage}}</i>页</a></li>
            </ul>
        </div>
    </div>
</template>
<script>
    import '../../assets/css/goolefont.css'
    import Cookies from 'js-cookie'
    function getCinemaListQueryParams (content) {
        return {
            brandId: content.$router.history.current.query.brandId || 99,
            areaId: content.$router.history.current.query.areaId || 99,
            halltypeId: content.$router.history.current.query.halltypeId || 99,
            pageSize: 12,
            nowPage: content.currentPage
        }
    }
    import {getData} from '../../plugins/axios'
    export default {
        props: [
            'seckillData1',
            'totalPage'
        ],
        data() {
            return {
                currentPage: 1,
                promoToken: '',
                // seckillData1: [
                //     {
                //         "uuid":1,
                //         "price":75,
                //         "startTime":"2019-8-19 08:00:00",
                //         "endTime":"2019-8-19 18:00:00",
                //         "status":-1,
                //         "stock":0,
                //         "description":"description",
                //         "cinemaId":0,
                //         "cinemaName":"万达影院(顺义店)",
                //         "cinemaAddress":"北京市顺义区华联金街购物中心"
                //     },
                //     {
                //         "uuid":2,
                //         "price":75,
                //         "startTime":"2019-8-19 08:00:00",
                //         "endTime":"2019-8-19 18:00:00",
                //         "status":-1,
                //         "stock":0,
                //         "description":"description",
                //         "cinemaId":0,
                //         "cinemaName":"万达影院(中关村店)",
                //         "cinemaAddress":"北京市中关村海龙大厦"
                //     },
                //     {
                //         "uuid":3,
                //         "price":75,
                //         "startTime":"2019-8-19 08:00:00",
                //         "endTime":"2019-8-19 18:00:00",
                //         "status":-1,
                //         "stock":0,
                //         "description":"description",
                //         "cinemaId":0,
                //         "cinemaName":"万达影院(大屯店)",
                //         "cinemaAddress":"北京市朝阳区大屯路50号金街商场"
                //     },
                //     {
                //         "uuid":4,
                //         "price":75,
                //         "startTime":"2019-8-19 08:00:00",
                //         "endTime":"2019-8-19 18:00:00",
                //         "status":-1,
                //         "stock":0,
                //         "description":"description",
                //         "cinemaId":0,
                //         "cinemaName":"万达影院(奥体中心店)",
                //         "cinemaAddress":"北京市朝阳区奥林匹克公园新奥购物中心"
                //     },
                //     {
                //         "uuid":5,
                //         "price":75,
                //         "startTime":"2019-8-19 08:00:00",
                //         "endTime":"2019-8-19 18:00:00",
                //         "status":-1,
                //         "stock":0,
                //         "description":"description",
                //         "cinemaId":0,
                //         "cinemaName":"万达影院(中南海店)",
                //         "cinemaAddress":"北京市东城区中南海52号"
                //     },
                //     {
                //         "uuid":6,
                //         "price":75,
                //         "startTime":"2019-8-19 08:00:00",
                //         "endTime":"2019-8-19 18:00:00",
                //         "status":-1,
                //         "stock":0,
                //         "description":"description",
                //         "cinemaId":0,
                //         "cinemaName":"万达影院(国贸店)",
                //         "cinemaAddress":"北京市朝阳区国贸CBD核心商场5012"
                //     },
                //     {
                //         "uuid":7,
                //         "price":75,
                //         "startTime":"2019-8-19 08:00:00",
                //         "endTime":"2019-8-19 18:00:00",
                //         "status":-1,
                //         "stock":0,
                //         "description":"description",
                //         "cinemaId":0,
                //         "cinemaName":"万达影院(123)",
                //         "cinemaAddress":"北京市朝阳区大屯路50号王道大厦"
                //     }
                // ]
            }
        },
        watch: {
            currentPage: function (oldValue, newValue) {
                const params = getCinemaListQueryParams(this);
                Cookies.set('cinemaNowPage', params.nowPage);
                this.$emit('getCinemaList', params);
            }
        },
        mounted() {
            // 将库存信息发布到缓存中
            this.publishStock();
        },
        inject: ['reload'],
        methods: {
            plus (stock , index) {
                if (this.seckillData1[index].number1 < 5 & this.seckillData1[index].number1 < stock) {
                    let newValue = this.seckillData1[index]
                    newValue.number1 = newValue.number1 +1
                    this.seckillData1.splice(index, 1, newValue)
                }
                console.log(this.seckillData1)
            },
            subtract(index){
                if (this.seckillData1[index].number1 > 1) {
                    let newValue = this.seckillData1[index]
                    newValue.number1 = newValue.number1 -1
                    this.seckillData1.splice(index, 1, newValue)
                }
            },
            // 将库存信息发布到缓存中
            publishStock () {
                let _this = this;
                let params = {
                };
                getData(process.env.baseUrl + '/promo/publishPromoStock', 'get', params)
                    .then((res) => {
                            if (res && res.status == 0) {
                            }else if (res.status != null) {
                               alert(res.msg)
                            //alert('同步缓存失败,请联系后台管理员')
                            }else {
                               alert('同步缓存失败,请联系后台管理员')
                            }
                }, (err) => {
                    console.log(err);
                })
            },
            //点击马上抢
            order (uuid , index) {
                if (Cookies.get('token')) {
                    this.getGenerateToken(uuid ,index)
                }else {
                    alert('请登录')
                }
            },
            createOrder (uuid ,index){
                let _this = this;
                let params = {
                    "promoId": uuid,
                    "amount":this.seckillData1[index].number1,
                    "promoToken": this.promoToken
                };
                getData(process.env.baseUrl + '/promo/createOrder', 'post', params)
                    .then((res) => {
                        if (res && res.status == 0) {
                          alert('秒杀成功,请查看订单')
                            this.againRequest()
                        } else if (res.status != null) {
                            alert(res.msg)
                            //alert('同步缓存失败,请联系后台管理员')
                        }else {
                            alert('同步缓存失败,请联系后台管理员')
                        }
                    }, (err) => {
                        console.log(err);
                    })

                // this.$router.go(0);
                // _this.reload()
            },
            getGenerateToken(uuid ,index) {
                let _this = this;
                let params = {
                    "promoId": uuid
                };
                getData(process.env.baseUrl + '/promo/generateToken', 'get', params)
                    .then((res) => {
                        if (res && res.status == 0) {
                            this.promoToken = res.msg
                            this.createOrder(uuid ,index)
                        } else {
                            alert('获取秒杀令牌失败')
                        }
                    }, (err) => {
                        console.log(err);
                    })
            },
            againRequest () {
                let params = {};
                getData(process.env.baseUrl + '/promo/getPromo', 'get', params).then((res) => {
                    if (res && res.status == 0) {
                        if (res.data && res.data.length > 0) {
                            for (let i = 0; i < res.data.length; i++) {
                               this.seckillData1.splice(i, 1, res.data[i])
                                for (const seckill of this.seckillData1) {
                                    seckill.number1=1
                                }
                            }
                        } else {
                            this.seckillData1 = [];
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
            },
            btnClick: function (data) {//页码点击事件
                const params = getCinemaListQueryParams(this);
                if (data != params.nowPage) {
                    params.nowPage = data
                }
                Cookies.set('cinemaNowPage', params.nowPage);
                this.$emit('getCinemaList', params);
            },
            pageClick: function () {
                // console.log('现在在' + this.currentPage + '页');
                const params = getCinemaListQueryParams(this);
                Cookies.set('cinemaNowPage', params.nowPage);
                this.$emit('getCinemaList', params);
            }
        },

        computed: {
            indexs: function () {
                let left = 1;
                let right = this.totalPage;
                let array = [];
                if (this.totalPage >= 5) {
                    if (this.currentPage > 3 && this.currentPage < this.totalPage - 2) {
                        left = this.currentPage - 2;
                        right = this.currentPage + 2;
                    } else {
                        if (this.currentPage <= 3) {
                            left = 1;
                            right = 5;
                        } else {
                            right = this.totalPage;
                            left = this.totalPage - 4;
                        }
                    }
                }
                while (left <= right) {
                    array.push(left);
                    left++
                }
                return array
            }

        }
    }
</script>
<style lang="scss" scoped>
    .seckill-list {
        margin: 30px auto;
        width: 1120px;
        .seckill-list-header {
            height: 20px;
            line-height: 20px;
            padding-left: 5px;
            margin-bottom: 30px;
            border-left: 5px solid #ff6637;
        }
        .seckill-cell {
            height: 240px;
            margin-bottom: 30px;
            padding-bottom: 20px;
            line-height: 90px;
            overflow: hidden;
            border-bottom: 1px dashed #666;
            .div-img {
                height: 220px;
                width: 220px;
                display: block;
                float: left;
                /*border: 4px solid white;*/
                background: #dbe1ec;
                .seckill-img{
                    width: 100%;
                    height: 100%;
                }
            }
            .div-description{
                width: 870px;
                height: 220px;
                display: block;
                float: left;
                margin-left: 30px;
                .left{
                    height: 180px;
                    width: 470px;
                    float: left;
                    .description{
                        height: 80px;
                        width: 470px;
                        margin-top: 10px;
                        margin-left: 20px;
                        float: left;
                        .description-title{
                            height: 70px;
                            line-height: 70px;
                            font-size: 28px;
                            display: block;
                            float: left;
                            /*margin-left: 20px;*/
                        }
                        .description-inner{
                            height: 80px;
                            font-size: 18px;
                            display: block;
                            float: left;
                            margin-left: 20px;
                        }
                    }
                    .cinemaName{
                        height: 24px;
                        width: 470px;
                        line-height: 24px;
                        font-size: 16px;
                        float: left;
                        margin-top: 10px;
                        margin-left: 20px;
                        color: #7f828b;
                    }
                    .cinemaAddress{
                        height: 24px;
                        width: 470px;
                        line-height: 24px;
                        float: left;
                        font-size: 16px;
                        margin-left: 20px;
                        color: #7f828b;
                    }
                    .time{
                        height: 24px;
                        width: 470px;
                        line-height: 24px;
                        float: left;
                        font-size: 16px;
                        margin-left: 20px;
                    }
                }
                .middle{
                    height: 220px;
                    width: 200px;
                    float: left;
                    background: #dbe1ec;
                    .stock-title{
                        height: 20px;
                        width: 160px;
                        margin-left: 10px;
                    }
                    .stock{
                        height: 180px;
                        width: 180px;
                        line-height: 200px;
                        text-align: center;
                        font-size: 50px;
                        color: darkorange;
                    }
                }
                .right{
                    height: 220px;
                    width: 180px;
                    float: right;
                    .old-price{
                        margin-top: 60px;
                        height: 20px;
                        padding-left: 60px;
                        font-size: 8px;
                        line-height: 20px;
                        text-decoration:line-through;
                        color: darkgrey;
                    }
                    .price{
                        height: 60px;
                        width: 180px;
                        color: red;
                        font-size: 30px;
                        padding-top: 0px;
                        line-height: 60px;
                        text-align: center;
                    }
                    .number{
                        height: 20px;
                        width: 180px;
                        line-height: 20px;
                        text-align: center;
                        /*background: #7f828b;*/
                        .plus{
                            height: 20px;
                            width: 60px;
                            float: left;
                            text-align: center;
                        }
                        .text{
                            height: 20px;
                            width: 60px;
                            float: left;
                            text-align: center;
                        }
                        .subtract{
                            height: 20px;
                            width: 60px;
                            float: left;
                            text-align: center;
                        }
                    }
                    .status{
                        height: 60px;
                        width: 178px;
                        font-size: 18px;
                        color: red;
                        line-height: 60px;
                        text-align: center;
                        border: 1px solid silver;
                        background: #FCDCDD;
                        border-radius:30px;
                    }
                }
            }
        }
        .no-seckill {
            margin-top: 40px;
            font-size: 16px;
            color: #333;
        }
    }

    .seckill-pager {
        text-align: center;
        .list-pager {
            li {
                display: inline-block;
                height: 30px;
                margin: 0 4px;
                border: 1px solid #d8d8d8;
                line-height: 30px;
                text-align: center;
                color: #999;
                a {
                    display: block;
                    padding: 0 12px;
                    font-size: 14px;
                    color: #333;
                }
                &.active {
                    border-color: #ff6637;
                    background-color: #ff6637;
                    a {
                        color: #fff;
                    }
                }
                &.sep {
                    padding: 0 12px;
                }
            }
        }
    }
</style>
