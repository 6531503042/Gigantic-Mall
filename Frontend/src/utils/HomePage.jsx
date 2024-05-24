
import NavBar from '../components/NavBar'
import TopCategories from '../components/TopCategories'
import Wrapper1 from '../components/WrapperHomePage'


function HomePage() {
    return (
        <div>
            <NavBar/>
            <div>
                    <Wrapper1/>
                    <TopCategories/>
            </div>
        </div>
    )
}

export default HomePage