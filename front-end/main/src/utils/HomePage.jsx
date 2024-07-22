import { Helmet } from 'react-helmet';
import NavBar from '../components/NavBar'
import TopCategories from '../components/TopCategories'
import Wrapper1 from '../components/WrapperHomePage'


function HomePage() {
    return (
        <div>
            <Helmet>
                <title>Giantic-Mall</title>
            </Helmet>
            <NavBar/>
            <div>
                    <Wrapper1/>
                    <TopCategories/>
            </div>
        </div>
    )
}

export default HomePage