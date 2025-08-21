import React from 'react';
import UserProfile from '../components/UserProfile';

const ProfilePage = () => {
  return (
    <div className="profile-page">
      <div className="container py-4">
        <div className="row">
          <div className="col-12">
            <h1 className="text-center mb-4">Mi Perfil</h1>
            <UserProfile />
          </div>
        </div>
      </div>
    </div>
  );
};

export default ProfilePage;