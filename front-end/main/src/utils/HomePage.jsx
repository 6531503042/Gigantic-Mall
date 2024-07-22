import { Helmet } from 'react-helmet';
import NavBar from '../components/NavBar'
import TopCategories from '../components/TopCategories'
import Wrapper1 from '../components/WrapperHomePage'
import JustForMe from '../components/JustForMe'
import Footer from '../components/Footer'
import Header from '../components/Header'
import TopElectronicsBrands from '../components/TopElectronicsBrands';
function HomePage() {
    return (
        <div>
            <Helmet>
                <title>Giantic-Mall</title>
            </Helmet>
            <Header/>
            <NavBar/>
            <div className='homepage'>
                    <Wrapper1/>
                    <TopCategories/>
                    <JustForMe/>
                    <TopElectronicsBrands/>
                    <Footer/>
            </div>
        </div>
    )
}

export default HomePage