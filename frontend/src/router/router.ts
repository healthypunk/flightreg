import { createRouter, createWebHistory } from 'vue-router'
import LoginView from '../views/LoginView.vue'
import SignUp from '../components/SignUp.vue'
import HomeView from '../views/HomeView.vue'
import FlightView from '../views/FlightView.vue'
import SeatView from '../views/SeatView.vue'

const router = createRouter({
    history: createWebHistory(import.meta.env.BASE_URL),
    routes:[
        {
            path: '/login',
            name: 'Login',
            component: LoginView
        },
        {
            path: '/signup',
            name: 'SignUp',
            component: SignUp
        },
        {
            path: '/',
            name: 'Home',
            component: HomeView
        },
        {
            path: '/flight',
            name: 'Flight',
            component: FlightView
        },
        {
            path: '/seats',
            name: 'Seats',
            component: SeatView
        }
    ]
})

export default router