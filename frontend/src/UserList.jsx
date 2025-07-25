// UsersList_withModalDetail.jsx
import React, { useEffect, useState } from "react";
import styled, { keyframes } from "styled-components";

// fade-in 애니메이션
const fadeIn = keyframes`
  from { opacity: 0; transform: translateY(24px);}
  to   { opacity: 1; transform: translateY(0);}
`;

// 제목 스타일
const ListTitle = styled.h1`
  text-align: center;
  font-size: 2.15rem;
  font-weight: 800;
  color: #20509b;
  letter-spacing: -0.04em;
  margin: 0 0 36px 0;
  padding-top: 48px;
  background: linear-gradient(90deg, #cae4ff 60%, #e6f0fa 100%);
  border-bottom: 1.5px solid #e0e7ef;
  box-shadow: 0 2px 8px rgba(64,100,200,0.06);
`;

// 카드 그리드 컨테이너
const CardGrid = styled.div`
  display: grid;
  grid-template-columns: repeat(3, 1fr);
  gap: 28px;
  padding: 40px 24px;
  background: #f8fafb;
  min-height: 100vh;

  @media (max-width: 1100px) {
    grid-template-columns: repeat(2, 1fr);
  }
  @media (max-width: 700px) {
    grid-template-columns: 1fr;
  }
`;

// 카드 스타일
const UserCard = styled.div`
  background: #fff;
  border-radius: 20px;
  box-shadow: 0 8px 28px 0 rgba(31, 41, 55, 0.13);
  padding: 32px 26px 28px 26px;
  display: flex;
  flex-direction: column;
  align-items: flex-start;
  animation: ${fadeIn} 0.9s cubic-bezier(.41,1.15,.56,1.08);
  transition: box-shadow 0.2s, transform 0.16s;
  position: relative;
  &:hover {
    box-shadow: 0 14px 36px 0 rgba(31, 41, 55, 0.18);
    transform: translateY(-4px) scale(1.025);
  }
`;

const UserName = styled.h2`
  font-size: 1.35rem;
  font-weight: 700;
  color: #2358b1;
  letter-spacing: -0.01em;
  margin: 0 0 6px 0;
  padding-bottom: 2px;
  border-bottom: 1.5px solid #e0e7ef;
  width: 100%;
`;

const CompanyBadge = styled.div`
  display: inline-block;
  background: linear-gradient(92deg, #e1ecfc 70%, #c4e3ff 100%);
  color: #1776e6;
  font-weight: 600;
  font-size: 0.93rem;
  padding: 4px 12px;
  border-radius: 15px;
  margin-top: 14px;
  margin-bottom: 18px;
  box-shadow: 0 2px 8px #e7eefc;
`;

const UserEmail = styled.div`
  display: flex;
  align-items: center;
  font-size: 1.03rem;
  color: #222;
  margin-bottom: 5px;
  margin-top: 2px;
  svg {
    margin-right: 7px;
    color: #3075d4;
    font-size: 1.13em;
  }
`;

// ===== 모달 스타일 개선 =====
const ModalOverlay = styled.div`
  position: fixed;
  inset: 0;
  background: rgba(34,46,68, 0.32);
  display: flex;
  align-items: center;
  justify-content: center;
  z-index: 100;
`;

const ModalBox = styled.div`
  background: #fff;
  border-radius: 22px;
  padding: 36px 38px 30px 38px;
  min-width: 370px;
  max-width: 98vw;
  max-height: 90vh;
  overflow-y: auto;
  box-shadow: 0 10px 44px 0 rgba(31,41,55,.23);
  position: relative;
  animation: ${fadeIn} 0.38s cubic-bezier(.41,1.15,.56,1.08);
  border-top: 8px solid #2176fa22;
`;

const CloseButton = styled.button`
  position: absolute;
  right: 18px;
  top: 14px;
  background: none;
  border: none;
  font-size: 2rem;
  color: #8ba7cb;
  cursor: pointer;
  &:hover {
    color: #2156ad;
    transform: scale(1.13);
  }
`;

const InfoTitle = styled.h2`
  color: #20509b;
  margin: 0 0 16px 0;
  font-size: 1.34rem;
  font-weight: 800;
  border-bottom: 1.5px solid #e0e7ef;
  padding-bottom: 8px;
`;

const InfoSection = styled.div`
  margin: 18px 0 28px 0;
  &:not(:last-child) {
    border-bottom: 1px dashed #e1e7f0;
    padding-bottom: 16px;
  }
`;
const InfoLabel = styled.span`
  display: inline-block;
  font-weight: 700;
  color: #2465b4;
  min-width: 90px;
  margin-bottom: 5px;
`;
const InfoText = styled.span`
  color: #23313b;
  font-size: 1.07rem;
`;
const SubLabel = styled.span`
  font-size: 0.98rem;
  font-weight: 400;
  color: #4b6eaf;
  margin-left: 10px;
`;

// 프로필 아이콘 (첫 글자 원형)
const Avatar = styled.div`
  width: 52px;
  height: 52px;
  background: #eaf1fd;
  border-radius: 50%;
  font-size: 2.1rem;
  color: #2465b4;
  font-weight: 800;
  display: flex;
  align-items: center;
  justify-content: center;
  margin-right: 20px;
  margin-bottom: 6px;
  box-shadow: 0 1px 6px #d5e2fa4c;
`;

// ===== 이메일 아이콘 =====
const EmailIcon = () => (
  <svg height="1em" width="1em" viewBox="0 0 24 24" fill="currentColor">
    <path d="M12 13.065L2 6.239V18h20V6.239l-10 6.826zM12 11.135l9.428-6.429A2 2 0 0 0 20 4H4a2 2 0 0 0-1.428.706L12 11.135z"/>
  </svg>
);

function UsersList() {
  const [users, setUsers] = useState([]);
  const [loading, setLoading] = useState(true);
  const [error, setError] = useState(null);
  const [selectedUser, setSelectedUser] = useState(null);

  useEffect(() => {
    fetch("https://jsonplaceholder.typicode.com/users")
      .then(res => {
        if (!res.ok) throw new Error("데이터 불러오기 실패");
        return res.json();
      })
      .then(data => {
        setUsers(data);
        setLoading(false);
      })
      .catch(err => {
        setError(err.message);
        setLoading(false);
      });
  }, []);

  const handleOverlayClick = e => {
    if (e.target === e.currentTarget) setSelectedUser(null);
  };

  const renderUserDetail = (user) => (
    <>
      {/* 프로필 & 이름 */}
      <div style={{display:'flex', alignItems:'center', marginBottom:'10px'}}>
        <Avatar>{user.name.charAt(0)}</Avatar>
        <div>
          <InfoTitle>{user.name}</InfoTitle>
          <InfoText style={{fontSize:"1.01rem", color:"#777"}}>{user.username}</InfoText>
        </div>
      </div>
      <InfoSection>
        <InfoLabel>이메일</InfoLabel>
        <InfoText>
          <EmailIcon style={{marginRight:6}} />
          {user.email}
        </InfoText>
      </InfoSection>
      <InfoSection>
        <InfoLabel>전화번호</InfoLabel>
        <InfoText>{user.phone}</InfoText>
      </InfoSection>
      <InfoSection>
        <InfoLabel>웹사이트</InfoLabel>
        <a href={`http://${user.website}`} target="_blank" rel="noopener noreferrer" style={{color:'#1762e5'}}>
          {user.website}
        </a>
      </InfoSection>
      <InfoSection>
        <InfoLabel>회사</InfoLabel>
        <InfoText>
          {user.company.name}<br />
          <SubLabel>슬로건</SubLabel>{user.company.catchPhrase}<br />
          <SubLabel>분야</SubLabel>{user.company.bs}
        </InfoText>
      </InfoSection>
      <InfoSection>
        <InfoLabel>주소</InfoLabel>
        <InfoText>
          {user.address.city}, {user.address.street}, {user.address.suite}, {user.address.zipcode}
        </InfoText>
        <div style={{marginTop:6}}>
          <SubLabel>좌표</SubLabel>
          <span style={{color:"#1a355e"}}>{user.address.geo.lat}, {user.address.geo.lng}</span>
        </div>
      </InfoSection>
      <div style={{textAlign:"right", color:"#a5adc0", fontSize:"0.98rem", marginTop:6}}>회원ID: {user.id}</div>
    </>
  );

  if (loading) return <p style={{padding:'32px', fontSize:'1.1rem'}}>로딩 중...</p>;
  if (error) return <p style={{padding:'32px', color:'#dc2626'}}>오류 발생: {error}</p>;

  return (
    <>
      <ListTitle>회원 목록</ListTitle>
      <CardGrid>
        {users.map(user => (
          <UserCard key={user.id} onClick={() => setSelectedUser(user)} style={{cursor:'pointer'}}>
            <UserName>{user.name}</UserName>
            <CompanyBadge>{user.company.name}</CompanyBadge>
            <UserEmail>
              <EmailIcon />
              {user.email}
            </UserEmail>
          </UserCard>
        ))}
      </CardGrid>
      {selectedUser && (
        <ModalOverlay onClick={handleOverlayClick}>
          <ModalBox>
            <CloseButton onClick={() => setSelectedUser(null)} title="닫기">&times;</CloseButton>
            {renderUserDetail(selectedUser)}
          </ModalBox>
        </ModalOverlay>
      )}
    </>
  );
}

export default UsersList;
