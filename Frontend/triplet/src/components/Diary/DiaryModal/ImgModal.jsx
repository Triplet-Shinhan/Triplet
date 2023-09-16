import { useEffect, useRef, useState } from 'react';
import './ImgModal.scss';
import { useMutation } from '@tanstack/react-query';
import { uploadImage } from '../../../api/DiaryApis';

export const ImgModal = ({ setModalOpen, tripId, dailyId, onImageUpload }) => {
  const [image, setImageSrc] = useState(null);
  const [imageFile, setImageFile] = useState(null);

  const closeModal = () => {
    setModalOpen(false);
  };

  const modalRef = useRef(null);

  useEffect(() => {
    const handler = (e) => {
      if (modalRef.current && !modalRef.current.contains(e.target)) {
        setModalOpen(false);
      }
    };

    document.addEventListener('mousedown', handler);

    return () => {
      document.removeEventListener('mousedown', handler);
    };
  });

  const handleSubmit = (e) => {
    e.preventDefault();
    updateImg.mutate({ imageFile, tripId, dailyId });
    closeModal();
  };

  const updateImg = useMutation(
    ({ imageFile, tripId, dailyId }) =>
      uploadImage({ imageFile, tripId, dailyId }),
    {
      onSuccess: (data) => {
        alert('업로드 완료');
        closeModal();

        onImageUpload(data);
      },
    }
  );

  const onUpload = (e) => {
    const file = e.target.files[0];
    setImageFile(file);

    const render = new FileReader();
    render.readAsDataURL(file);

    return new Promise((resolve) => {
      render.onload = () => {
        setImageSrc(render.result || null);
        resolve();
      };
    });
  };

  return (
    <section ref={modalRef} className="imageContainer">
      <nav className="imageNav">
        <div className="imageHeader">사진</div>
        <button className="deleteModal" onClick={closeModal}>
          X
        </button>
      </nav>
      <form className="imgForm" method="POST" onSubmit={handleSubmit}>
        <section className="imgSec">
          <input
            type="file"
            id="chooseImg"
            name="chooseImg"
            accept="image/*"
            onChange={onUpload}
          />
          <label className="findBtn" htmlFor="chooseImg">
            찾아보기
          </label>
        </section>
        <section className="imgPreview">
          <div className="imgText">미리보기</div>
          <img className="imgView" src={image} />
          <button className="imgAdd">추가</button>
        </section>
      </form>
    </section>
  );
};
