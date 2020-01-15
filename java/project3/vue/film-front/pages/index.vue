<template>
  <!-- <section> 标签定义了文档的某个区域。比如章节、头部、底部或者文档的其他区域。-->
  <section class="container">
    <Banner :homeBanner="homeData.data"></Banner>
    <Container :homeData="homeData"></Container>
  </section>
</template>
<script>
     // 引用其他组件
    import Container from '~/components/index/Container.vue'
    import Banner from '~/components/index/Banner.vue'
    import {getData} from '../plugins/axios'

    export default {
        // head : 定义页属性
        head() {
            return {
                // 页名
                title: 'Mtime影院',
                // meta: meta是head区的一个辅助性标签。
                //        其主要作用有：搜索引擎优化（SEO），
                //        定义页面使用语言，自动刷新并指向新的页面，
                //        实现网页转换时的动态效果，控制页面缓冲，
                //        网页定级评价，控制网页显示的窗口等！
                meta: [
                    {hid: '首页', name: '首页', content: '首页'}
                ]
            }
        },
        // components 标签引用
        components: {
            Banner,
            Container,
        },
        // middleware : 中间件,el:指向middleware文件夹下auth文件
        middleware: "auth",
        // 页面组件参数
        data() {
            return {
                homeData: {
                    banners: [],
                    boxRanking: [],
                    expectRanking: {},
                    top100: {},
                    hotFilms: {},
                    soonFilms: {}
                }
            }
        },
        // 生命周期函数
        created() {
            this.getIndex();
        },
        // 组件方法区
        methods: {
            getIndex () {
                getData(process.env.baseUrl + '/film/getIndex', 'get').then((res) => {
                    if (res && res.status == 0) {
                        this.homeData = res;
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
  .container {
    padding-top: 61px;
  }
</style>
