import SampleStore from './SampleStore';

const context = describe;

describe('SampleStore', () => {
  let sampleStore;

  beforeEach(() => {
    sampleStore = new SampleStore();
  });

  describe('GetBear', () => {
    context('BearType이 home일 경우', () => {
      it('HomeBear를 받아온다', async () => {
        expect(sampleStore.bear).toBeFalsy();

        const bearType = 'home';

        await sampleStore.getBear(bearType);

        expect(sampleStore.bear).toBeTruthy();
      });
    });

    context('BearType이 user일 경우', () => {
      it('UserBear를 받아온다', async () => {
        expect(sampleStore.bear).toBeFalsy();

        const bearType = 'user';

        await sampleStore.getBear(bearType);

        expect(sampleStore.bear).toBeTruthy();
      });
    });

    context('BearType이 admin일 경우', () => {
      it('AdminBear를 받아온다', async () => {
        expect(sampleStore.bear).toBeFalsy();

        const bearType = 'admin';

        await sampleStore.getBear(bearType);

        expect(sampleStore.bear).toBeTruthy();
      });
    });
  });
});
