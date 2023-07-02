import usePaymentStore from '../hooks/usePaymentsStore';

export default function DownLoadForm() {
  const paymentStore = usePaymentStore();
  const handleDownLoad = async () => {
    const { data } = await paymentStore.getPaymentData();
  };
  return (
    <>
      <p>
        Download Payments
      </p>
      <button type="button" onClick={handleDownLoad}>다운로드</button>
    </>
  );
}
