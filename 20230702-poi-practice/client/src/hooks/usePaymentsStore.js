import { paymentStore } from '../stores/PaymentStore';
import useStore from './useStore';

export default function usePaymentStore() {
  return useStore(paymentStore);
}
