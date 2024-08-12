import { todoStore } from '../stores/TodoStore';
import useStore from './useStore';

export default function useTodoStore() {
  return useStore(todoStore);
}
