<template>
    <section class="cinema-div">
        <div class="header-placeholder" style="height: 81px;" data-v-36ae7e9a=""></div>
        <div class="profile-container">
            <!-- 个人中心 start -->
            <div class="pc-left__aside" data-before="个人中心">
                <ul>
                    <li>
                        <nuxt-link to="/myorder" class="orders txt active">我的订单</nuxt-link>
                    </li>
                    <li>
                        <nuxt-link to="/profile" class="profile txt">基本信息</nuxt-link>
                    </li>
                </ul>
            </div>
            <!-- 个人中心 end -->
            <!-- 我的订单 start -->
            <div class="orders-container" v-if="orderInfo">
                <div class="profile-title">我的订单</div>

                <div class="order-box" v-for="(item, index) in orderInfo" :key="index">
                    <div class="order-header">
                        <span class="order-date">{{item.orderTimestamp}}</span>
                        <span class="order-id">订单号:{{item.orderId}}</span>
                        <!--<span class="del-order" data-orderid="3233291685"></span>-->
                    </div>

                    <div class="order-body">
                        <div class="poster">
                            <img width="100" height="95" src="../assets/img/order-p.png">
                        </div>

                        <div class="order-content">
                            <div class="movie-name">{{item.filmName}}</div>
                            <div class="cinema-name">{{item.cinemaName}}</div>
                            <div class="hall-ticket">
                                <!--<span>1号厅</span>-->
                                <!--<span v-for="(item, index) in seatData.totalSeatsArr" :key="index" class="">-->
                                    <!--{{item.row + 1}}排{{item.column + 1}}座-->
                                <!--</span>-->
                            </div>
                            <div class="show-time">{{item.fieldTime}}</div>
                            <div class="show-time">{{item.startTime}}{{item.endTime}}</div>
                            <!--<div class="show-time"></div>-->
                        </div>

                        <div class="order-price">￥{{item.orderPrice}}</div>

                        <div class="order-status">{{item.orderStatus}}</div>

                        <div class="actions">
                            <div>
                                <!--<a href="javascript:;" class="order-detail" data-act="orders-detail-click" data-bid="b_y190atas">查看详情</a>-->
                            </div>
                        </div>
                    </div>
                </div>

                <div class="seckill-pager" v-if="orderInfo && orderInfo.length > 0">
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
                <div class="orders-pager">

                </div>
            </div>
            <!-- 我的订单 end -->
        </div>
    </section>
</template>
<!-- 引入样式 -->
<script>
    import { getData } from '../plugins/axios'
    export default {
        head () {
            return {
                title: '我的订单',
                meta: [
                    { hid: '我的订单', name: '我的订单', content: '我的订单' }
                ]
            }
        },
        data () {
            return {
                orderInfo: {},
                currentPage: 1,
                totalPage: 1,
                nowPage: 1
            }
        },
        mounted () {
            this.getPayResult();
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

        },
        methods: {
            btnClick: function (data) {//页码点击事件
                this.nowPage = data
                this.currentPage = data
                this.getPayResult()
            },
            pageClick: function () {
                this.nowPage = this.currentPage
                this.getPayResult()
            },


            formatDate(timestamp) {
                //var date = new Date(timestamp * 1000);//时间戳为10位需*1000，时间戳为13位的话不需乘1000
                var date = new Date(timestamp);//时间戳为10位需*1000，时间戳为13位的话不需乘1000
                var Y = date.getFullYear() + '-';
                var M = (date.getMonth()+1 < 10 ? '0'+(date.getMonth()+1) : date.getMonth()+1) + '-';
                var D = date.getDate() + ' ';
                var h = date.getHours() + ':';
                var m = date.getMinutes() + ':';
                var s = date.getSeconds();
                return Y+M+D+h+m+s;
            },
            getPayResult () {
                let params = {
                    nowPage: this.nowPage,
                    pageSize: 5
                }, _this = this;

                // getData('https://api.myjson.com/bins/tus5c', 'get', params).then((res) => {
                getData(process.env.baseUrl + '/order/getOrderInfo', 'post', params).then((res) => {
                    if (res && res.status == 0) {
                        _this.orderInfo = res.data;
                        console.log(res)
                        console.log(res.totalPage)
                        _this.totalPage = res.totalPage
                        _this.orderInfo.map((item) => {
                            //item.orderTimestamp = _this.formatDate(item.orderTimestamp);
                            item.orderTimestamp = item.orderTimestamp;
                        })
                    } else {
                        if (res.msg) {
                            alert(res.msg)
                        }
                    }
                }, (err) => {
                    console.log(err);
                })
            }
        }
    }
</script>
<style lang="scss" scoped>
    .profile-container {
        width: 1200px;
        margin: 0 auto;
        position: relative;
        background-color: #fff;
        &:after {
            content: '';
            display: table;
            clear: both;
        }
        .pc-left__aside {
            float: left;
            width: 200px;
            min-height: 800px;
            background-color: #f4f3f4;
            text-align: center;
            border-right: 1px solid #e1e1e1;
            &:before {
                content: attr(data-before);
                display: block;
                margin:30px 0;
                font-size: 22px;
                color: #222;
                font-weight: 400;
            }
            ul {
                margin:0;
                padding:0;
                li {
                    list-style: none;
                    .txt {
                        display: block;
                        font-weight: 400;
                        color: #333;
                        height: 40px;
                        line-height: 40px;
                        font-size: 18px;
                        text-decoration: none;
                        &.active {
                            color: #fff;
                            background-color: #ff6637;
                        }
                    }
                }
            }
        }
        .orders-container {
            float: left;
            padding-left: 40px;
            width: 922px;
            min-height: 900px;
            .profile-title {
                padding: 26px 0;
                color: #ff6637;
                font-size: 18px;
                border-bottom: 1px solid #e1e1e1;
                margin-bottom: 30px;
            }
            .order-box {
                border: 1px solid #e5e5e5;
                margin: 0 40px 30px 0;
                .order-header {
                    background-color: #f7f7f7;
                    font-size: 14px;
                    padding: 16px 20px;
                    .order-date {
                        color: #333;
                        display: inline-block;
                        margin-right: 30px;
                    }
                    .order-id {
                        color: #999;
                    }
                    .del-order {
                        width: 15px;
                        height: 16px;
                        background: url("/assets/img/remove.png") no-repeat;
                        float: right;
                    }
                }
                .order-body {
                    padding: 20px;
                    padding-right: 0;
                    >div {
                        display: inline-block;
                        vertical-align: top;
                    }
                    .poster {
                        border: 2px solid #fff;
                        -webkit-box-shadow: 0 1px 2px 0 hsla(0,0%,53%,.5);
                        box-shadow: 0 1px 2px 0 hsla(0,0%,53%,.5);
                        margin-right: 11px;
                        font-size: 0;
                        /*img {*/
                            /*width: 100%;*/
                            /*height: 100%;*/
                        /*}*/
                    }
                    .order-content {
                        width: 55%;
                        /*width: 49%;*/
                        .movie-name {
                            font-size: 16px;
                            font-weight: 700;
                            color: #333;
                            margin: 4px 0 7px -6px;
                        }
                        .cinema-name, .hall-ticket {
                            font-size: 12px;
                            color: #999;
                            margin-bottom: 4px;

                        }
                        .show-time {
                            font-size: 12px;
                            color: #ff6637;
                            margin-bottom: 4px;
                        }
                    }

                    .order-price,.order-status {
                        font-size: 14px;
                        color: #333;
                        width: 12%;
                        line-height: 95px;
                    }
                    .actions {
                        line-height: 95px;
                        text-align: center;
                        .order-detail {
                            color: #333;
                            vertical-align: middle;
                        }
                    }
                }
            }
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
