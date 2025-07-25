//기본 라이브러리 선언
import React, {useEffect, useState} from 'react';
function MemberList(){
    //사용할 변수 선언 (useState)
    const [members, setMembers]=useState([]);
    const [loading, setLoading]=useState(true);
    const [error, setError]=useState(null);

    //전송받은 데이터 처리 (useEffect)
    useEffect(()=>{
        fetch('/api/members')
        .then(resp =>resp.json())
    
        .then((data)=>{
            setMembers(data);
            setLoading(false);
        })
        .catch((err)=>{
            setError(err.message);
            setLoading(false);
        })
    },[]);

    //상태 확인 
    if(loading) return <p>로딩 중 ...</p>
    if(error) return <p>오류 발생: {error}</p>
    
    //구현할 페이지 작성
    return (
            <ul>
                {members.map((member)=>(
                    <li key={member.username}>
                        {member.username} - {member.password} - {member.email}
                    </li>
                ))}
            </ul>
    );
}

//내보내기
export default MemberList;
