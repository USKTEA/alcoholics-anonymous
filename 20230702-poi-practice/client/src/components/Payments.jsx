import { useState } from 'react';
import usePaymentStore from '../hooks/usePaymentsStore';

export default function Payments() {
  const [loading, setLoading] = useState(false);
  const paymentStore = usePaymentStore();

  const handleExcelDownload = async () => {
    if (loading) {
      return;
    }

    setLoading(true);

    try {
      const response = await paymentStore.getPaymentsExcel();

      if (response != null) {
        const fileName = response.body.headers['content-disposition'].split('filename=')[1];
        // @ts-ignore
        if (window.navigator && window.navigator.msSaveOrOpenBlob) {
          // IE variant
          // @ts-ignore
          window.navigator.msSaveOrOpenBlob(
            new Blob([response.body.data], {
              type: 'application/vnd.openxmlformats-officedocument.spreadsheetml.sheet',
            }),
            fileName,
          );
        } else {
          const url = window.URL.createObjectURL(
            new Blob([response.body.data], {
              type: 'application/vnd.openxmlformats-officedocument.spreadsheetml.sheet',
            }),
          );
          const link = document.createElement('a');
          link.href = url;
          link.setAttribute('download', response.body.headers['content-disposition'].split('filename=')[1]);
          document.body.appendChild(link);
          link.click();
        }
      }
    } catch (error) {
      console.error(']-----] Orders::handleExcelDownload.error [-----[', error);
    } finally {
      setLoading(false);
    }
  };

  const handleAddPayments = async () => {
    await paymentStore.addPayments();
  };
  return (
    <>
      <p>
        Payments
      </p>
      {loading ? null : <button type="button" onClick={handleExcelDownload}>다운로드</button>}
      {' '}
      {loading ? null : <button type="button" onClick={handleAddPayments}>데이터 10,000건 넣기</button>}
      <p>
        총 payments:
        {paymentStore.total}
        건
      </p>
    </>
  );
}
