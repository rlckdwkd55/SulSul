import { useNavigate } from 'react-router-dom'; 
import styled from 'styled-components';
import ProductList from '../template/productList';
import BestItemSlider from '../template/BestItemSlider';
import { rootColor } from '../../Util/GlobalStyle';

const MainWrap = styled.div`
    max-width: 1144px;
    margin: auto;
`;
const BestItemsWrap = styled.div`
    margin: auto;
`;
const NewItemsWrap = styled.div`
    margin: auto;
    margin-top: 100px;
`;
const ContentHeadWrap = styled.div`
    > span {
        font-weight: bold;
        color: ${rootColor.color};
    }
`;

const ContentHead = (props) => {
    const navigate = useNavigate();
    return (
        <ContentHeadWrap>
            <span>{props.title}</span>
            <span style={{float: 'right', marginRight: '35px', cursor: 'pointer'}}>
                <span onClick={()=>{
                    navigate('/list', {state: {cate: props.cate}});
                }}>더보기</span>
                <i className="fa-solid fa-chevron-right wrapper-header rightItem"></i>
            </span>
        </ContentHeadWrap>
    )
}

const Main = () => {

    return(
        <MainWrap>
            <div id="banner">

            </div>
            <BestItemsWrap>
                <ContentHead title={'BEST PICK!'} cate={'best'} />
                <BestItemSlider/>
            </BestItemsWrap>
            <NewItemsWrap>
                <ContentHead title={'NEW!'} cate={'new'} />
                <ProductList itemKey={'newItems'} />
            </NewItemsWrap>
        </MainWrap>
    );
}

export default Main