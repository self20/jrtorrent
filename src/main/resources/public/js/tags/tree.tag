<tree>
    <div class="resizeable" style="height: {25 * totalChildrenToShow || 0}px">
        <tree_entry each={opts.entries}></tree_entry>
    </div>
    <style scoped>
        :scope {
            display: block;
            overflow: hidden;
            background: #F8F8F8;
        }

        .resizeable {
            transition: height .2s cubic-bezier(.4, 0, .2, 1);
        }
    </style>

    <script>
        var that = this;
        var opened = false;
        var hash = opts.hash;
        var totalChildrenToShow = 0;
        var totalChildrenHidden = 0;

        this.on('mount', function () {
            that.totalChildrenHidden = opts.entries.length;
            that.update();
        });

        that.updateShownChildsCount = function (innerChilds) {
//            console.log('tree.updateShownChildsCount ' + innerChilds)
            if (typeof(innerChilds) != "undefined")
                that.totalChildrenToShow = that.totalChildrenToShow + innerChilds;
            console.log('tree.totalChildrenToShow ' + that.totalChildrenToShow)
        };

        that.toggle = function () {
            that.openned = !that.openned;
            console.log('toggle tree: '+that.openned);
            if (!that.openned) {
                that.totalChildrenHidden = that.totalChildrenToShow;
                that.totalChildrenToShow = 0;
            } else {
                that.totalChildrenToShow = that.totalChildrenHidden;
            }
            that.update();
        };

    </script>
</tree>